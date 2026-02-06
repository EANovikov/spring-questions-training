package com.xevgnov.entity.repository;

import com.xevgnov.entity.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ArticlePersistenceContextInfoRepositoryImpl implements ArticlePersistenceContextInfoRepository<Article, UUID> {

    @PersistenceContext
    private EntityManager entityManager;

    // checks if an entity is present in the persistent context    @Override
    public boolean isEntityInPersistenceContext(Article entity) {
        return entityManager.contains(entity);
    }

    // checks if an entity is present in the persistent context
    @Override
    public boolean isEntityInPersistenceContextById(UUID entityId) {
        return entityManager.contains(entityManager.find(Article.class, entityId));
    }
}
