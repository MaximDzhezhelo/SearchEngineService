package com.kiev.makson.searchengineservice.service.impl;

import com.kiev.makson.searchengineservice.exception.SearchEngineException;
import com.kiev.makson.searchengineservice.helper.CommonHelper;
import com.kiev.makson.searchengineservice.model.dto.DocumentDto;
import com.kiev.makson.searchengineservice.model.dto.TokenDto;
import com.kiev.makson.searchengineservice.model.entity.Document;
import com.kiev.makson.searchengineservice.model.entity.Token;
import com.kiev.makson.searchengineservice.model.repository.DocumentRepository;
import com.kiev.makson.searchengineservice.model.repository.TokenRepository;
import com.kiev.makson.searchengineservice.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.kiev.makson.searchengineservice.helper.CommonHelper.isValidIdentificationKey;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class DocumentStorageService implements StorageService<DocumentDto> {

    public static final String SPLITTER = " ";

    private final DocumentRepository documentRepository;
    private final TokenRepository tokenRepository;

    public DocumentStorageService(DocumentRepository documentRepository, TokenRepository tokenRepository) {
        this.documentRepository = documentRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String save(final MultipartFile file) throws IOException {
        final String filename = file.getOriginalFilename();
        final Set<String> values = parseFile(file.getBytes());

        final Document document = documentRepository.saveAndFlush(Document.valueOf(filename));

        final List<Token> tokens = values.stream()
                .map(value -> Token.valueOf(value, document))
                .collect(toList());

        try {

            insertAll(tokens);
        } catch (Exception ex) {
            documentRepository.delete(document);
            throw new SearchEngineException(ex);
        }

        return document.getIdentificationKey();
    }

    @Override
    public DocumentDto get(final String key) {
        if (!isValidIdentificationKey(key)) throw new SearchEngineException();

        final Document document = documentRepository.findByIdentificationKey(key)
                .stream()
                .findAny()
                .orElseThrow(SearchEngineException::new);

        final List<Token> tokens = tokenRepository.findByDocument(document);
        return DocumentDto.valueOf(document.getDocumentName(), tokens);
    }

    @Override
    public Set<String> getKeys(final List<TokenDto> tokens) {
        return tokens.stream()
                .map(TokenDto::getToken)
                .filter(CommonHelper::isValidToken)
                .map(tokenRepository::findByToken)
                .flatMap(Collection::stream)
                .map(Token::getDocument)
                .map(Document::getIdentificationKey)
                .collect(toSet());
    }

    private Set<String> parseFile(final byte[] fileBytes) {
        try (final InputStream is = new ByteArrayInputStream(fileBytes);
             final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            return br.lines()
                    .map(line -> line.split(SPLITTER))
                    .flatMap(Arrays::stream)
                    .collect(toSet());

        } catch (IOException | RuntimeException ex) {
            throw new SearchEngineException(ex);
        }
    }

    @Transactional
    void insertAll(final List<Token> tokens) { tokenRepository.save(tokens); }

}
