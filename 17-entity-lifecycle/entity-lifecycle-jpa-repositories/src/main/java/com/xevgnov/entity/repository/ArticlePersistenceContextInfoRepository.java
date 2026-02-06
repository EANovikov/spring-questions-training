package com.xevgnov.entity.repository;

public interface ArticlePersistenceContextInfoRepository<T, ID> {
    boolean isEntityInPersistenceContext(T entity);

    boolean isEntityInPersistenceContextById(ID entityId);
}
