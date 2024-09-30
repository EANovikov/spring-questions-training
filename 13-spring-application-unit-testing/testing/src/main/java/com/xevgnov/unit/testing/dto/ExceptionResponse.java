package com.xevgnov.unit.testing.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ExceptionResponse {
    
    private String message;
    private String details;
}
