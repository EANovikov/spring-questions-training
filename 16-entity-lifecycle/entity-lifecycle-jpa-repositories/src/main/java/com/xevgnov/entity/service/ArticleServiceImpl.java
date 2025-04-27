package com.xevgnov.entity.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.xevgnov.entity.dto.ArticleDto;
import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.exception.ArticleNotFoundException;
import com.xevgnov.entity.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto get(UUID id) {
        // state [Managed (Persistent)]
        Article article = getIfExists(id);
        log.info("Got article {}", article);
        return mapToArticleDto(article);
    }

    @Override
    public List<ArticleDto> getAll() {
        // state [Managed (Persistent)]
        return articleRepository
                .findAll(Sort.by(Sort.Direction.DESC, "created"))
                .stream().map(article -> mapToArticleDto(article))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        // state [Managed (Persistent)]
        Article article = getIfExists(id);
        // state [Managed (Persistent)] -> [Removed]
        articleRepository.delete(article);
        // state [Removed]
        log.info("Deleted article {}", article);
    }

    @Override
    public void update(ArticleDto articleDto, UUID id) {
        // state [Managed (Persistent)]
        Article article = getIfExists(id);
        article.setTitle(articleDto.getTitle());
        article.setText(articleDto.getText());
        article.setUpdated(Instant.now());
        Article updatedArticle = articleRepository.save(article);
        // state [Managed (Persistent)]
        log.info("Updated article {}", updatedArticle);
    }

    @Override
    public ArticleDto create(ArticleDto articleDto) {
        // state [New (Transient)]
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .text(articleDto.getText())
                .created(Instant.now())
                .build();
        // state [New (Transient)] -> [Managed (Persistent)]
        Article createdArticle = articleRepository.save(article);
        // state [Managed (Persistent)]
        log.info("Created article {}", createdArticle);
        return mapToArticleDto(createdArticle);
    }

    private Article getIfExists(UUID id) {
        Optional<Article> article = articleRepository.findById(id);
        log.info("Found article {}", article);
        if (article.isEmpty()) {
            throw new ArticleNotFoundException(
                    String.format("Article %s does not exist", id));
        }
        return article.get();
    }

    private ArticleDto mapToArticleDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .text(article.getText())
                .build();
    }

}
