package com.kiev.makson.searchengineservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "DOCUMENTS")
public class Document {
    @GenericGenerator(name = "documentSequence",
        strategy = "sequence",
            parameters = @Parameter(name = "sequence", value = "DOCUMENT_ID_SEQ")
    )
    @Id
    @Column(name = "DOCUMENT_ID")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "documentSequence")
    private Long documentId;

    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Column(name = "IDENTYFICATION_KEY")
    private String identificationKey;

    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getIdentificationKey() { return identificationKey; }
    public void setIdentificationKey(String identificationKey) {
        this.identificationKey = identificationKey;
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
    public void pre() {
        if (identificationKey == null)
            identificationKey = UUID.randomUUID().toString();
    }
}