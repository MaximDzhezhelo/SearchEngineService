package com.kiev.makson.searchengineservice.helper;

import org.junit.Test;

import java.util.UUID;

import static com.kiev.makson.searchengineservice.helper.CommonHelper.isValidIdentificationKey;
import static com.kiev.makson.searchengineservice.helper.CommonHelper.isValidToken;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonHelperTest {

    @Test
    public void testCheckIdentificationKey(){
        assertFalse(isValidIdentificationKey(""));
        assertFalse(isValidIdentificationKey(null));
        assertFalse(isValidIdentificationKey("sf"));
        assertFalse(isValidIdentificationKey("esfdgc"));
        assertFalse(isValidIdentificationKey(" "));

        assertTrue(isValidIdentificationKey(UUID.randomUUID().toString()));
    }

    @Test
    public void testCheckValidToken(){
        assertFalse(isValidToken(""));
        assertFalse(isValidToken(null));
        assertFalse(isValidToken(" "));

        assertTrue(isValidToken("."));
        assertTrue(isValidToken("text"));
    }

}