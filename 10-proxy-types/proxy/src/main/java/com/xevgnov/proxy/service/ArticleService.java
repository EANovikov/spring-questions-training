package com.xevgnov.proxy.service;

import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.entity.Article;

public interface ArticleService {
    Article create(ArticleDto articleDto);
    
}
