package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.RunnerTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.kiev.makson.searchengineservice.controller.SearchEngineController.URL_SEARCH_ENGINE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@WebMvcTest(SearchEngineController.class)
public class SearchEngineControllerTest extends RunnerTest {

//    @Autowired
//    private MockMvc mvc;

    @Test
    @Ignore
    public void testRequest() throws Exception {
        mockMvc.perform(get(URL_SEARCH_ENGINE + "/33131"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}