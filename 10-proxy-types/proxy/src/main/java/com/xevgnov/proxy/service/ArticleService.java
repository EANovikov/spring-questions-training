package com.xevgnov.proxy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.entity.Article;

public interface ArticleService {
    ArticleDto get(UUID id);
    List<ArticleDto> getAll();
    ArticleDto create(ArticleDto articleDto);
    
}
