package com.kiev.makson.searchengineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class SearchEngineService {

    public static void main(String[] args) { SpringApplication.run(SearchEngineService.class); }

}
