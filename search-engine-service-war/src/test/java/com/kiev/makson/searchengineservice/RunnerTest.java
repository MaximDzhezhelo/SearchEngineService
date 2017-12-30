package com.kiev.makson.searchengineservice;

import com.kiev.makson.searchengineservice.SearchEngineService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchEngineService.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
public abstract class RunnerTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @PostConstruct
    public void init(){mockMvc  = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }

}
