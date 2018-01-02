package com.kiev.makson.searchengineservice.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity
@Table(name = "DOCUMENTS")
public class Document {
    @GenericGenerator(name = "documentSequence",
        strategy = "sequence",
            parameters = @Parameter(name = "sequence", value = "DOCUMENT_ID_SEQ")
    )
    @Id
    @Column(name = "DOCUMENT_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "documentSequence")
    private Long documentId;

    @Column(name = "DOCUMENT_NAME", length = 50, nullable = false)
    private String documentName;
    @Column(name = "IDENTYFICATION_KEY")
    private String identificationKey;

    @Transient
    private List<Token> tokens;

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getIdentificationKey() { return identificationKey; }
    public void setIdentificationKey(String identificationKey) {
        this.identificationKey = identificationKey;
    }

    public List<Token> getTokens() { return tokens; }
    public void setTokens(List<Token> tokens) { this.tokens = tokens; }

    public static Document valueOf(final String fileName){
        final Document document = new Document();
        document.setDocumentName(fileName);
        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId) &&
                Objects.equals(documentName, document.documentName) &&
                Objects.equals(identificationKey, document.identificationKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(documentId, documentName, identificationKey);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", identificationKey='" + identificationKey + '\'' +
                '}';
    }

    @PrePersist
    public void setUpdate() { if (isNull(identificationKey)) identificationKey = UUID.randomUUID().toString(); }

}