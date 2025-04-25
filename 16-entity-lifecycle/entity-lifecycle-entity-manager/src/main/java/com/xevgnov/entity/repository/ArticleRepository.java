package com.xevgnov.entity.repository;

import com.xevgnov.entity.entity.Article;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository {

    Article get(UUID id);

    List<Article> getAll();

    Article create(Article article);

    Article update(Article article);

    void detach(Article article);

    void delete(UUID id);
}
