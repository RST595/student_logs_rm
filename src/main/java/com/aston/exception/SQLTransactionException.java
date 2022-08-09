package com.aston.exception;

public class SQLTransactionException extends RuntimeException{
    public SQLTransactionException(String message) {
        super(message);
    }
}
