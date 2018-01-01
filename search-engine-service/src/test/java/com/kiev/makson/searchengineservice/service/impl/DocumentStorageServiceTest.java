package com.kiev.makson.searchengineservice.service.impl;

import com.kiev.makson.searchengineservice.model.repository.DocumentRepository;
import com.kiev.makson.searchengineservice.model.repository.TokenRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class DocumentStorageServiceTest {

    protected static final String FILE_NAME = "testFile";
    private static final String DOCUMENT = " token ken a da none tok ";

    private DocumentRepository documentRepository = mock(DocumentRepository.class);
    private TokenRepository tokenRepository = mock(TokenRepository.class);

}