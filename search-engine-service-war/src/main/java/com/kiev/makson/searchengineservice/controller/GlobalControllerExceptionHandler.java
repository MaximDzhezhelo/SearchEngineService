package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.exception.SearchEngineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String BAD_REQUEST = "We are sorry but your request contains bad syntax and cannot be fulfilled";

    @ExceptionHandler(SearchEngineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleSearchEngineException(SearchEngineException e) {
        return BAD_REQUEST;
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String handleIOException(SearchEngineException e) {
        return BAD_REQUEST;
    }
}
