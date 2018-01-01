package com.kiev.makson.searchengineservice.model.repository;

import com.kiev.makson.searchengineservice.model.entity.Document;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Long> {

    Document saveAndFlush(Document document);

    List<Document> findByIdentificationKey(String identificationKey);
}
