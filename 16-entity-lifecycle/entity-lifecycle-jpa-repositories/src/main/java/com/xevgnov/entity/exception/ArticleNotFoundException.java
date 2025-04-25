package com.xevgnov.entity.exception;


public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message){
        super(message);
    }
}
