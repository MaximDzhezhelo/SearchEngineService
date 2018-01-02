package com.kiev.makson.searchengineservice.model.dto;

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
    public String toString() {
        return "TokenDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
