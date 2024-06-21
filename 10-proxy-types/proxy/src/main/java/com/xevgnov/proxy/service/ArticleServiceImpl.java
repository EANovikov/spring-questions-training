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
        Optional<Article> article = articleRepository.findById(id);
        log.info("Found article {}", article);
        if(article.isEmpty()){
            throw new ArticleNotFoundException(
                String.format("Article %s does not exist", id));
        }
        return mapFromArticle(article.get());
    }
   

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository
        .findAll(Sort.by(Sort.Direction.DESC, "created"))
        .stream().map(article -> mapFromArticle(article))
        .toList();
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
        return mapFromArticle(createdArticle);
    }

    private ArticleDto mapFromArticle(Article article){
      return ArticleDto.builder()
        .id(article.getId())
        .title(article.getTitle())
        .text(article.getText())
        .build();
    }
}
