package com.xevgnov.entity.repository;

import com.xevgnov.entity.dto.ArticleDto;
import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.exception.ArticleNotFoundException;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final EntityManager entityManager;

    public ArticleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Article get(UUID id) {
        Article article = entityManager.find(Article.class, id);
     //   log.info("Article ID {} present in Persistence Context: {}", id, entityManager.contains(article));
        log.info("Found article {}", article);
        if (article == null) {
            throw new ArticleNotFoundException(
                    String.format("Article %s does not exist", id));
        }
        return article;
    }

    @Override
    public List<Article> getAll() {
        String jpqlQuery = "SELECT a FROM Article a ORDER BY a.created DESC";
        // states [Managed (Persistent)]
        return entityManager.createQuery(jpqlQuery, Article.class)
                .getResultStream()
                .toList();
    }

    @Override
    public Article create(Article article) {
   //     log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [New (Transient)] -> [Managed (Persistent)]
        entityManager.persist(article);
        log.info("Created article {}", article);
        // state [Managed (Persistent)]
        entityManager.flush();
     //   log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        return article;
    }

    @Override
    public Article update(Article article) {
    //    log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        Article updatedArticle = entityManager.merge(article);
        log.info("Updated article {}", updatedArticle);
     //   log.info("Article ID {} present in Persistence Context: {}", updatedArticle.getId(), entityManager.contains(updatedArticle));
        return updatedArticle;
    }

    @Override
    public void detach(Article article) {
   //     log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [Managed (Persistent)] -> [Detached]
        entityManager.flush();
        entityManager.detach(article);
        // state [ [Detached]
  //      log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
    }

    @Override
    public void delete(UUID id) {
        // state [Managed (Persistent)]
        Article article = get(id);
    //    log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [Managed (Persistent)] -> [Removed]
        entityManager.remove(article);
        entityManager.flush();
    //    log.info("Article ID {} present in Persistence Context: {}", article.getId(), entityManager.contains(article));
        // state [Removed]
        log.info("Deleted article {}", article);
    }

}
