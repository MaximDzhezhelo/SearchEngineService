package com.kiev.makson.searchengineservice.model.repository;

import com.kiev.makson.searchengineservice.model.entity.Document;
import com.kiev.makson.searchengineservice.model.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TokenRepository extends CrudRepository<Token, Long> {
    List<Token> findByDocument (Document document);

    Set<Token> findByToken(String string);
}
