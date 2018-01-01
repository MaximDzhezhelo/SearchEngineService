package com.kiev.makson.searchengineservice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchEngineService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
public abstract class RunnerTest extends AbstractTransactionalJUnit4SpringContextTests{

    protected static final String FILE_NAME = "testFile.txt";
    protected static final String DOCUMENT = "test sets rest tser ar ra na fa ha";

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    private MultipartFile getMultipartFile(final Path path) {
        final String name = FILE_NAME;
        final String originalFileName = FILE_NAME;
        final String contentType = "text/plain";
        byte[] content = DOCUMENT.getBytes();
        return new MockMultipartFile(name, originalFileName, contentType, content);
    }

    @PostConstruct
    public void init(){mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }

}
