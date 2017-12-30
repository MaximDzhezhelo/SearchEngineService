package com.kiev.makson.searchengineservice.controller;

import com.kiev.makson.searchengineservice.exception.SearchEngineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(SearchEngineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    String exceptionHandler(SearchEngineException e) {
        return "";  //todo implement it
    }

}
