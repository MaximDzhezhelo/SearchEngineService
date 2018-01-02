package com.kiev.makson.searchengineservice.service;

import com.kiev.makson.searchengineservice.model.dto.TokenDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface StorageService<T> {

    String save(final MultipartFile file) throws IOException;

    T get(final String key);

    Set<String> getKeys(final List<TokenDto> tokens);

}
