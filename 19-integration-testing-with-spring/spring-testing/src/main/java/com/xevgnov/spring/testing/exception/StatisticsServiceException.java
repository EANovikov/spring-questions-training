package com.xevgnov.spring.testing.exception;

public class StatisticsServiceException extends RuntimeException {
    
    public StatisticsServiceException(String message, Throwable e) {
        super(message, e);
    }

}
