package com.xevgnov.entity.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xevgnov.entity.dto.ArticleDto;
import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.exception.ArticleNotFoundException;
import com.xevgnov.entity.repository.ArticleRepository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ArticleServiceEntityManagerImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceEntityManagerImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto get(UUID id) {
       Article article = articleRepository.get(id);
        return mapToArticleDto(article);
    }

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository.getAll()
                .stream()
                .map(this::mapToArticleDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Article article = articleRepository.get(id);
        articleRepository.delete(article);
        log.info("Deleted article {}", article);
    }

    @Override
    public void update(ArticleDto articleDto, UUID id) {
        Article article = articleRepository.get(id);
        articleRepository.detach(article);
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        articleRepository.update(article);
    }

    @Override
    public ArticleDto create(ArticleDto articleDto) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        article = articleRepository.create(article);
        return mapToArticleDto(article);
    }

    private ArticleDto mapToArticleDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .text(article.getText())
                .build();
    }

}
