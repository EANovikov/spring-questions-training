package com.xevgnov.proxy.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ArticleDto {
    
    private UUID id;
    @NotBlank(message = "A title cannot be empty")
    private String title;
    @NotBlank(message = "A content cannot be empty")
    private String text;

}
