package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.model.dto.DocumentDto;
import com.kiev.makson.searchengineservice.model.dto.TokenDto;
import com.kiev.makson.searchengineservice.model.entity.Token;
import com.kiev.makson.searchengineservice.service.StorageService;
import com.kiev.makson.searchengineservice.service.impl.DocumentStorageService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kiev.makson.searchengineservice.controller.SearchEngineController.URL_SEARCH_ENGINE;

@RestController
@RequestMapping(URL_SEARCH_ENGINE)
public class SearchEngineController {

    public static final String URL_SEARCH_ENGINE = "/search-engine";
    public static final String REQUEST_FILE_PARAM = "data";
    public static final String MEDIA_TYPE = "application/octet-stream";
    public static final String HEADER_KEY = "Content-Disposition";
    public static final String HEADER_VALUE = "attachment; filename=";

    private final StorageService<DocumentDto> storageService;

    public SearchEngineController(DocumentStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{identificationKey}")
    public ResponseEntity<InputStreamResource> getDocument(@PathVariable String identificationKey) {
        final DocumentDto dto = storageService.get(identificationKey);

        final String content = createContent(dto.getTokens());

        return ResponseEntity.ok()
                .header(HEADER_KEY, HEADER_VALUE + dto.getName())
                .contentLength(content.length())
                .contentType(MediaType.parseMediaType(MEDIA_TYPE))
                .body(new InputStreamResource(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8))));
    }

    @PostMapping
    public ResponseEntity<?> putDocument(@RequestParam(REQUEST_FILE_PARAM) final MultipartFile file) throws IOException {
        final String identificationKey = storageService.save(file);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(identificationKey).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<?> searchDocumentIdentificationKey(@RequestBody final List<TokenDto> tokens) {
        Set<String> keys = storageService.getKeys(tokens);

        if (keys.isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    private String createContent(final List<Token> tokenSet){
        if(Objects.isNull(tokenSet)) return "";

        return tokenSet.stream()
                .map(Token::getToken)
                .collect(Collectors.joining(" "));
    }

}
