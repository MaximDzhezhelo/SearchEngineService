package com.kiev.makson.searchengineservice.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TOKENS")
public class Token {

    @GenericGenerator(name = "tokenSequence",
            strategy = "sequence",
            parameters = @Parameter(name = "sequence", value = "TOKEN_ID_SEQ")
    )
    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "tokenSequence")
    private Long tokenId;

    @Column(name = "TOKEN", length = 50, nullable = false)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    public Long getTokenId() { return tokenId; }
    public void setTokenId(Long tokenId) { this.tokenId = tokenId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }

    public static Token valueOf(final String value, final Document document){
        final Token token = new Token();
        token.setToken(value);
        token.setDocument(document);
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(tokenId, token1.tokenId) &&
                Objects.equals(token, token1.token) &&
                Objects.equals(document, token1.document);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tokenId, token, document);
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", token='" + token + '\'' +
                ", document=" + document +
                '}';
    }
}
