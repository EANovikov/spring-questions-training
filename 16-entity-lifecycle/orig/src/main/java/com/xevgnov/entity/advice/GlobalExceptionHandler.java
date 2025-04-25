package com.xevgnov.entity.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xevgnov.entity.dto.ExceptionDto;
import com.xevgnov.entity.exception.ArticleNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ArticleNotFoundException e) {
        ExceptionDto response = ExceptionDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
