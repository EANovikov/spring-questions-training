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
// comment ArticleServiceImpl @Primary and use ArticleServiceEntityManagerImpl
// to see the EntityManager implementation
// shows detach call in article update which is not covered in
// ArticleServiceImpl
// @Primary
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
        // state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [Managed (Persistent)] -> [Removed]
        entityManager.remove(article);
        entityManager.flush();
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [Removed]
        log.info("Deleted article {}", article);
    }

    @Override
    @Transactional
    public void update(ArticleDto articleDto, UUID id) {
        // state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        log.info("Starting to update article {}", article);
        // state [Managed (Persistent)] -> [Detached]
        entityManager.flush();
        entityManager.detach(article);
        // state [ [Detached]
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        // state [Managed (Persistent)]
        article = entityManager.merge(article);
        log.info("Updated article {}", article);
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
    }

    @Override
    @Transactional
    public ArticleDto create(ArticleDto articleDto) {
        // state [New (Transient)]
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [New (Transient)] -> [Managed (Persistent)]
        entityManager.persist(article);
        log.info("Created article {}", article);
        // state [Managed (Persistent)]
        entityManager.flush();
        log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        return mapToArticleDto(article);
    }

    private Article getIfExists(UUID id) {
        Article article = entityManager.find(Article.class, id);
        log.info("Found article {}", article);
        if (article == null) {
            throw new ArticleNotFoundException(
                    String.format("Article %s does not exist", id));
        }
        return article;
    }

    private ArticleDto mapToArticleDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .text(article.getText())
                .build();
    }

}
