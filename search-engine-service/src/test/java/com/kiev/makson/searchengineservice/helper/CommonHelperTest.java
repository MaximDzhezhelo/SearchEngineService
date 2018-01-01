package com.kiev.makson.searchengineservice.helper;

import org.junit.Test;

import java.util.UUID;

import static com.kiev.makson.searchengineservice.helper.CommonHelper.*;
import static org.junit.Assert.*;

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

}