package com.xevgnov.proxy.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.entity.Article;
import com.xevgnov.proxy.exception.ArticleNotFoundException;
import com.xevgnov.proxy.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    

    @Override
    public ArticleDto get(UUID id) {
        return mapToArticleDto(getIfExists(id));
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
        articleRepository.delete(getIfExists(id));
    }

    @Override
    public void update(ArticleDto articleDto, UUID id) {
        Article article = getIfExists(id);
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        articleRepository.save(article);
    }



    @Override
    public ArticleDto create(ArticleDto articleDto) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        Article createdArticle = articleRepository.save(article);
        log.info("Created article {}", article);
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
