package com.xevgnov.proxy.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.entity.Article;
import com.xevgnov.proxy.exception.ArticleNotFoundException;
import com.xevgnov.proxy.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ObjectMapper objectMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ObjectMapper objectMapper) {
        this.articleRepository = articleRepository;
        this.objectMapper = objectMapper;
    }

    

    @Override
    public ArticleDto get(UUID id) {
        Article article = getIfExists(id);
        log.info("Got article {}", articleAsJson(article));
        return mapToArticleDto(article);
    }
   

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository
        .findAll(Sort.by(Sort.Direction.DESC, "created"))
        .stream().map(article -> mapToArticleDto(article))
        .toList();
    }

    @Override
    public void delete(UUID id) {
        Article article = getIfExists(id);
        articleRepository.delete(article);
        log.info("Deleted article {}", articleAsJson(article));
    }

    @Override
    @Transactional
    public void update(ArticleDto articleDto, UUID id) {
        Article article = getIfExists(id);
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        articleRepository.save(article);
        log.info("Updated article {}", articleAsJson(article));
    }



    @Override
    public ArticleDto create(ArticleDto articleDto) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        Article createdArticle = articleRepository.save(article);
        log.info("Created article {}", articleAsJson(article));
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

    private String articleAsJson(Article article){
        try{
        return objectMapper.writeValueAsString(article);
        } catch (JsonProcessingException e){
            log.warn("Cannot convert article {} to JSON", article);
            return article.toString();
        }
    }
}
