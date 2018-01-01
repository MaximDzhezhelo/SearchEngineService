package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.exception.SearchEngineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String BAD_REQUEST = "We are sorry but your request contains bad syntax and cannot be fulfilled";

    @ExceptionHandler(SearchEngineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String exceptionHandler(SearchEngineException e) {
        return BAD_REQUEST;
    }

}
