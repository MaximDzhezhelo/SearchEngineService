package com.kiev.makson.searchengineservice.model.repository;

import com.kiev.makson.searchengineservice.model.entity.Document;
import com.kiev.makson.searchengineservice.model.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Set<Token> findByDocument (Document document);
}
