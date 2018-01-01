package com.kiev.makson.searchengineservice.exception;

public class SearchEngineException extends RuntimeException{

    public SearchEngineException() { }

    public SearchEngineException(String message) { super(message); }

    public SearchEngineException(String message, Throwable cause) { super(message, cause); }

    public SearchEngineException(Throwable cause) { super(cause); }
}
