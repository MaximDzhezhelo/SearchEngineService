package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.RunnerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;

import static com.kiev.makson.searchengineservice.controller.GlobalControllerExceptionHandler.BAD_REQUEST;
import static com.kiev.makson.searchengineservice.controller.SearchEngineController.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SearchEngineControllerTest extends RunnerTest {

    private final MockMultipartFile file = new MockMultipartFile(REQUEST_FILE_PARAM, FILE_NAME, "text/plain", DOCUMENT.getBytes());

    @Test
    public void testPutDocument() throws Exception {
        // put document and get identificationKey
        mockMvc.perform(fileUpload(URL_SEARCH_ENGINE)
                .file(file)
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetDocument() throws Exception {
        // put document (document with same name exists) and get unique identificationKey

        final MvcResult mvcResult_A = mockMvc.perform(fileUpload(URL_SEARCH_ENGINE)
                .file(file)
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        final String identificationKey = getIdentificationKeyFromResponse(mvcResult_A.getResponse());
        assertNotNull(identificationKey);
        assertFalse(identificationKey.isEmpty());

        final MvcResult mvcResult_B = mockMvc.perform(get(URL_SEARCH_ENGINE + "/" + identificationKey))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final MockHttpServletResponse response = mvcResult_B.getResponse();
        final String header = response.getHeader(HEADER_KEY);
        assertTrue(header.contains(FILE_NAME));

        final byte[] contentAsByteArray = response.getContentAsByteArray();
        final String fileContent = new String(contentAsByteArray, "UTF-8");
        System.out.println(">12>" + fileContent + "<<");
    }

    @Test
    public void testGetDocument_InvalidIdentificationKey() throws Exception {
        mockMvc.perform(get(URL_SEARCH_ENGINE + "/invalid"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(BAD_REQUEST));
    }

    private String getIdentificationKeyFromResponse(final MockHttpServletResponse response) {
        final String[] split = response.getRedirectedUrl()
                .split("/");
        return split[split.length - 1];
    }

}