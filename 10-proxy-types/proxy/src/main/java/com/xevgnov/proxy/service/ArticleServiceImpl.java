package com.xevgnov.proxy.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xevgnov.proxy.config.ApplicationConfig;
import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.entity.Article;
import com.xevgnov.proxy.exception.ArticleNotFoundException;
import com.xevgnov.proxy.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ApplicationConfig configuration;

    public ArticleServiceImpl(ArticleRepository articleRepository, ApplicationConfig configuration) {
        this.articleRepository = articleRepository;
        this.configuration = configuration;
    }

    

    @Override
    public ArticleDto get(UUID id) {
        Article article = getIfExists(id);
        log.info("Got article {}", configuration.asJson(article));
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
        log.info("Deleted article {}", configuration.asJson(article));
    }

    @Override
    public void update(ArticleDto articleDto, UUID id) {
        Article article = getIfExists(id);
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        Article updatedArticle = articleRepository.save(article);
        log.info("Updated article {}", configuration.asJson(updatedArticle));
    }



    @Override
    public ArticleDto create(ArticleDto articleDto) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        Article createdArticle = articleRepository.save(article);
        log.info("Created article {}", configuration.asJson(createdArticle));
        return mapToArticleDto(createdArticle);
    }

    private Article getIfExists(UUID id){
        Optional<Article> article = articleRepository.findById(id);
        log.info("Found article {}", configuration.asJson(article));
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
