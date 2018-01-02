package com.kiev.makson.searchengineservice.model.dto;

import java.util.Objects;

public class TokenDto {

    private String token;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public static TokenDto valueOf(final String value){
        final TokenDto dto = new TokenDto();
        dto.setToken(value);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDto tokenDto = (TokenDto) o;
        return Objects.equals(token, tokenDto.token);
    }

    @Override
    public int hashCode() {

        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
