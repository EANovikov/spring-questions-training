package com.xevgnov.n.plus.one.custom.query.dto;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private UUID id;
    private String title;
    private String text;
    private Instant created;
    private Instant updated;

    @JsonIgnoreProperties({ "articles" })
    // @JsonBackReference
    private AuthorDto author;
}
