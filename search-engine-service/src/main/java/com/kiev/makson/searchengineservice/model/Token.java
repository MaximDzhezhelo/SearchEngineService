package com.kiev.makson.searchengineservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity(name = "TOKENS")
public class Token {

    @GenericGenerator(name = "tokenSequence",
            strategy = "sequence",
            parameters = @Parameter(name = "sequence", value = "TOKEN_ID_SEQ")
    )
    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "tokenSequence")
    private Long tokenId;

    @Column(name = "TOKEN")
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_ID")
    private Document document;

    public Long getTokenId() { return tokenId; }
    public void setTokenId(Long tokenId) { this.tokenId = tokenId; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Document getDocument() { return document; }
    public void setDocument(Document document) { this.document = document; }
}
