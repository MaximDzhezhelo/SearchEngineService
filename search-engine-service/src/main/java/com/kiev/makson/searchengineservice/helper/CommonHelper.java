package com.kiev.makson.searchengineservice.helper;

import java.util.Objects;
import java.util.UUID;

public class CommonHelper {

    public static boolean isValidIdentificationKey(final String key) {
        if (Objects.isNull(key) || key.trim().isEmpty()) return false;

        try {
            UUID.fromString(key);
            return true;
        }catch (Exception e) { return  false; }

    }

    public static boolean isValidToken(final String token) {
        if (Objects.isNull(token) || token.trim().isEmpty()) return false;
        return true;
    }
}
