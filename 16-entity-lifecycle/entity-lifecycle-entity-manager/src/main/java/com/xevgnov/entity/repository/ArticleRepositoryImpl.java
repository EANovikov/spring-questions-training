package com.xevgnov.entity.repository;

import com.xevgnov.entity.entity.Article;
import com.xevgnov.entity.exception.ArticleNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

// EntityManager implementation
@Slf4j
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Article get(UUID id) {
        Article article = entityManager.find(Article.class, id);
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
        // state [New (Transient)] -> [Managed (Persistent)]
        entityManager.persist(article);
        entityManager.flush();
        // state [Managed (Persistent)]
        log.info("Created article {}", article);
        return article;
    }

    @Override
    public Article update(Article article) {
        Article updatedArticle = entityManager.merge(article);
        entityManager.flush();
        // state [Managed (Persistent)]
        log.info("Updated article {}", updatedArticle);
        return updatedArticle;
    }

    @Override
    public void detach(Article article) {
        // state [Managed (Persistent)] -> [Detached]
        entityManager.flush();
        entityManager.detach(article);
        // state [ [Detached]
    }

    @Override
    public void delete(Article article) {
        // state [Managed (Persistent)] -> [Removed]
        entityManager.remove(article);
        entityManager.flush();
        // state [Removed]
        log.info("Deleted article {}", article);
    }

}
