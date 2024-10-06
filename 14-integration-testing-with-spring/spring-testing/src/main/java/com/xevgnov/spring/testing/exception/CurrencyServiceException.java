package com.xevgnov.spring.testing.exception;

public class CurrencyServiceException extends RuntimeException {

    public CurrencyServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
