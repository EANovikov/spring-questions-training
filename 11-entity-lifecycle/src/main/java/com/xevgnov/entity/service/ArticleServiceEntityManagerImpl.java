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
// @Primary
public class ArticleServiceEntityManagerImpl implements ArticleService {

    private final EntityManager entityManager;

    public ArticleServiceEntityManagerImpl(ArticleRepository articleRepository, EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    

    @Override
    public ArticleDto get(UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Got article {}", article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [Managed (Persistent)] -> [Detached]
        entityManager.detach(article);
        //state [ [Detached]
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        return mapToArticleDto(article);
    }
   

    @Override
    public List<ArticleDto> getAll() {
        String jpqlQuery = "SELECT a FROM Article a ORDER BY a.created DESC"; 
        //states [Managed (Persistent)]
        return entityManager.createQuery(jpqlQuery, Article.class)
        .getResultStream()
        .map(article -> mapToArticleDto(article))
        .toList();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [Managed (Persistent)] -> [Removed]
        entityManager.remove(article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [Removed]
        log.info("Deleted article {}", article);
    }

    @Override
    @Transactional
    public void update(ArticleDto articleDto, UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        //state [Managed (Persistent)]
        entityManager.merge(article);
        log.info("Updated article {}", article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
    }

    @Override
    @Transactional
    public ArticleDto create(ArticleDto articleDto) {
        //state [New (Transient)]
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [New (Transient)] -> [Managed (Persistent)]  
        entityManager.persist(article);
        log.info("Created article {}", article);
        //state [Managed (Persistent)]     
        entityManager.flush();
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        return mapToArticleDto(article);
    }

    private Article getIfExists(UUID id){
        Article article = entityManager.find(Article.class, id);
        log.info("Found article {}", article);
        if(article == null){
            throw new ArticleNotFoundException(
                String.format("Article %s does not exist", id));
        }
        return article;
    }

    private ArticleDto mapToArticleDto(Article article){
      return ArticleDto.builder()
        .id(article.getId())
        .title(article.getTitle())
        .text(article.getText())
        .build();
    }

}
