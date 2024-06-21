package com.xevgnov.proxy.service;

import java.util.List;
import java.util.UUID;

import com.xevgnov.proxy.dto.ArticleDto;

public interface ArticleService {
    ArticleDto get(UUID id);
    List<ArticleDto> getAll();
    ArticleDto create(ArticleDto articleDto);
    void update(ArticleDto articleDto, UUID id);
    void delete(UUID id);
}
