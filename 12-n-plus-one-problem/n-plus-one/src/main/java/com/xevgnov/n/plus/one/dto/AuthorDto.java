package com.xevgnov.n.plus.one.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.xevgnov.n.plus.one.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
   private UUID id;
    private String name;
    private String surname;
    @JsonIgnoreProperties({"author"})
   // @JsonManagedReference
    private List<ArticleDto> articles;
}
