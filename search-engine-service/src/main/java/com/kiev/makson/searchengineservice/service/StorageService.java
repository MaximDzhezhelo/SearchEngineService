package com.kiev.makson.searchengineservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService<T> {

    String save(final MultipartFile file) throws IOException;

    T get(final String key);

}
