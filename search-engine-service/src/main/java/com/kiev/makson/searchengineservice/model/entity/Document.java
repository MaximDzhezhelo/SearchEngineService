package com.kiev.makson.searchengineservice.model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Set;
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

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Set<Token> tokenSet;

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getIdentificationKey() { return identificationKey; }
    public void setIdentificationKey(String identificationKey) {
        this.identificationKey = identificationKey;
    }

    public Set<Token> getTokenSet() { return tokenSet; }
    public void setTokenSet(Set<Token> tokenSet) { this.tokenSet = tokenSet; }

    public static Document valueOf(final String fileName){
        final Document document = new Document();
        document.setDocumentName(fileName);
        return document;
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