package com.xevgnov.entity.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class PersistenceContextInfoRepositoryImpl implements PersistenceContextInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isInPersistenceContext(Object entity) {
        return entityManager.contains(entity);
    }

}
