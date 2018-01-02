package com.kiev.makson.searchengineservice.model.dto;

import com.kiev.makson.searchengineservice.model.entity.Token;

import java.util.List;
import java.util.Objects;

public class DocumentDto {

    private String name;
    private List<Token> tokens;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Token> getTokens() { return tokens; }
    public void setTokens(List<Token> tokens) { this.tokens = tokens; }

    public static DocumentDto valueOf(final String name, final List<Token> tokens) {
        final DocumentDto dto = new DocumentDto();
        dto.setName(name);
        dto.setTokens(tokens);
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto dto = (DocumentDto) o;
        return Objects.equals(name, dto.name) &&
                Objects.equals(tokens, dto.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tokens);
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "name='" + name + '\'' +
                ", tokens=" + tokens +
                '}';
    }
}
