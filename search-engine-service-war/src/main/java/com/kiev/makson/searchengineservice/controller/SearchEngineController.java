package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.repository.SearchEngineRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kiev.makson.searchengineservice.controller.SearchEngineController.URL_SEARCH_ENGINE;

@RestController(URL_SEARCH_ENGINE)
public class SearchEngineController {

    public static final String URL_SEARCH_ENGINE = "/search-engine";

    private final SearchEngineRepository engineRepository;

    public SearchEngineController(SearchEngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @GetMapping(value = "/{documentName}")
    public void getDocument(@PathVariable Long documentName) {

    }

    @PostMapping
    public void putDocument() {

    }

}
