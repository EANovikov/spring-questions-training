package com.xevgnov.entity.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xevgnov.entity.dto.ArticleDto;
import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.exception.ArticleNotFoundException;
import com.xevgnov.entity.repository.ArticleRepository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Primary
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private EntityManager entityManager;

    public ArticleServiceImpl(ArticleRepository articleRepository, EntityManager entityManager) {
        this.articleRepository = articleRepository;
        this.entityManager = entityManager;
    }
    

    @Override
    public ArticleDto get(UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Got article {}", article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        return mapToArticleDto(article);
    }
   

    @Override
    public List<ArticleDto> getAll() {
        //state [Managed (Persistent)]
        return articleRepository
        .findAll(Sort.by(Sort.Direction.DESC, "created"))
        .stream().map(article -> mapToArticleDto(article))
        .toList();
    }

    @Override
    public void delete(UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [Managed (Persistent)] -> [Removed]
        articleRepository.delete(article);
        //state [Removed]
        log.info("Deleted article {}", article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
    }

    @Override
    public void update(ArticleDto articleDto, UUID id) {
        //state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        Article updatedArticle = articleRepository.save(article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", updatedArticle.getId(), entityManager.contains(updatedArticle));
        //state [Managed (Persistent)]
        log.info("Updated article {}", updatedArticle);
    }

    @Override
    public ArticleDto create(ArticleDto articleDto) {
        //state [New (Transient)]
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        log.info("Article ID {} present in JPA's Persistence Context: {}", article.getId(), entityManager.contains(article));
        //state [New (Transient)] -> [Managed (Persistent)]          
        Article createdArticle = articleRepository.save(article);
        log.info("Article ID {} present in JPA's Persistence Context: {}", createdArticle.getId(), entityManager.contains(createdArticle));
        //state [Managed (Persistent)] 
        log.info("Created article {}", createdArticle);
        return mapToArticleDto(createdArticle);
    }

    private Article getIfExists(UUID id){
        Optional<Article> article = articleRepository.findById(id);
        log.info("Found article {}", article);
        if(article.isEmpty()){
            throw new ArticleNotFoundException(
                String.format("Article %s does not exist", id));
        }
        return article.get();
    }

    private ArticleDto mapToArticleDto(Article article){
      return ArticleDto.builder()
        .id(article.getId())
        .title(article.getTitle())
        .text(article.getText())
        .build();
    }

}
