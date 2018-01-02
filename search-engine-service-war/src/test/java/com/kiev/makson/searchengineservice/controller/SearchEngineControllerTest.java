package com.kiev.makson.searchengineservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiev.makson.searchengineservice.RunnerTest;
import com.kiev.makson.searchengineservice.model.dto.TokenDto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static com.kiev.makson.searchengineservice.controller.GlobalControllerExceptionHandler.BAD_REQUEST;
import static com.kiev.makson.searchengineservice.controller.SearchEngineController.*;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchEngineControllerTest extends RunnerTest {

    private final MockMultipartFile file_A = new MockMultipartFile(REQUEST_FILE_PARAM, FILE_NAME_A, "text/plain", DOCUMENT_A.getBytes());
    private final MockMultipartFile file_B = new MockMultipartFile(REQUEST_FILE_PARAM, FILE_NAME_B, "text/plain", DOCUMENT_B.getBytes());
    private final MockMultipartFile file_C = new MockMultipartFile(REQUEST_FILE_PARAM, FILE_NAME_C, "text/plain", DOCUMENT_C.getBytes());
    private final MockMultipartFile file_D = new MockMultipartFile(REQUEST_FILE_PARAM, FILE_NAME_D, "text/plain", DOCUMENT_D.getBytes());

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test_A_PutDocument() throws Exception {
        // put document and get identificationKey
        executePost(file_A);
    }

    @Test
    public void test_B_GetDocument() throws Exception {
        final MvcResult mvcResult_A = executePost(file_A);

        final String identificationKey = getIdentificationKeyFromResponse(mvcResult_A.getResponse());
        assertNotNull(identificationKey);
        assertFalse(identificationKey.isEmpty());

        final MvcResult mvcResult_B = mockMvc.perform(get(URL_SEARCH_ENGINE + "/" + identificationKey))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        final MockHttpServletResponse response = mvcResult_B.getResponse();
        final String header = response.getHeader(HEADER_KEY);
        assertTrue(header.contains(FILE_NAME_A));

        final byte[] contentAsByteArray = response.getContentAsByteArray();
        final String fileContent = new String(contentAsByteArray, StandardCharsets.UTF_8);

        assertNotNull(fileContent);
        assertFalse(fileContent.isEmpty());
    }

    @Test
    public void test_C_GetDocument_InvalidIdentificationKey() throws Exception {
        mockMvc.perform(get(URL_SEARCH_ENGINE + "/invalid"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(BAD_REQUEST));
    }

    @Test
    public void test_D_SearchDocumentIdentificationKey() throws Exception {

        uploadDocuments();

        final TokenDto tokenDto_A = TokenDto.valueOf(SEARCH_VALUE_A);
        final TokenDto tokenDto_B = TokenDto.valueOf("3");

        final MvcResult mvcResult = executePut(tokenDto_A, tokenDto_B, status().isOk());

        final List<String> keys = getDocumentIdentificationKeys(mvcResult);

        assertNotNull(keys);
        assertFalse(keys.isEmpty());
        assertEquals(3, keys.size());
    }

    @Test
    public void test_E_SearchDocumentIdentificationKey() throws Exception {

        uploadDocuments();

        final TokenDto tokenDto_A = TokenDto.valueOf(SEARCH_VALUE_B);
        final TokenDto tokenDto_B = TokenDto.valueOf("3");

        final MvcResult mvcResult = executePut(tokenDto_A, tokenDto_B, status().isOk());

        final List<String> keys = getDocumentIdentificationKeys(mvcResult);

        assertNotNull(keys);
        assertFalse(keys.isEmpty());
        assertEquals(2, keys.size());
    }

    @Test
    public void test_F_SearchDocumentIdentificationKey() throws Exception {
        //search value doesn't present
        uploadDocuments();

        final TokenDto tokenDto_A = TokenDto.valueOf(SEARCH_VALUE_C);
        final TokenDto tokenDto_B = TokenDto.valueOf("3");

        executePut(tokenDto_A, tokenDto_B, status().isNotFound());
    }

    @Test
    public void test_G_SearchDocumentIdentificationKey() throws Exception {
        //invalid search value
        uploadDocuments();

        final TokenDto tokenDto_A = TokenDto.valueOf("  ");
        final TokenDto tokenDto_B = TokenDto.valueOf("  ");

        executePut(tokenDto_A, tokenDto_B, status().isNotFound());
    }

    private MvcResult executePut(final TokenDto tokenDto_A, final TokenDto tokenDto_B, final ResultMatcher status) throws Exception {
        return mockMvc.perform(put(URL_SEARCH_ENGINE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(tokenDto_A, tokenDto_B))))
                .andDo(print())
                .andExpect(status)
                .andReturn();
    }

    private MvcResult executePost(final MockMultipartFile file) throws Exception {
        return mockMvc.perform(fileUpload(URL_SEARCH_ENGINE)
                .file(file)
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    private String getIdentificationKeyFromResponse(final MockHttpServletResponse response) {
        final String[] split = response.getRedirectedUrl()
                .split("/");
        return split[split.length - 1];
    }

    private List<String> getDocumentIdentificationKeys(final MvcResult mvcResult) throws IOException {
        final byte[] byteArray = mvcResult.getResponse()
                .getContentAsByteArray();
        final JsonNode jsonNode = objectMapper.readTree(byteArray);

        assertTrue(jsonNode.isArray());

        return StreamSupport.stream(jsonNode.spliterator(), false)
                .map(JsonNode::asText)
                .collect(toList());
    }

    private void uploadDocuments() throws Exception {
        executePost(file_A);
        executePost(file_B);
        executePost(file_C);
        executePost(file_D);
    }
}