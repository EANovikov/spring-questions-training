package com.xevgnov.entity.service;

import java.util.List;
import java.util.UUID;

import com.xevgnov.entity.dto.ArticleDto;

public interface ArticleService {
    ArticleDto get(UUID id);
    List<ArticleDto> getAll();
    ArticleDto create(ArticleDto articleDto);
    void update(ArticleDto articleDto, UUID id);
    void delete(UUID id);
}
