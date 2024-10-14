package com.xevgnov.spring.testing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private String details;
    
}
