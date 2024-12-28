package com.xevgnov.entity.repository;

public interface PersistenceContextInfoRepository {
    boolean isInPersistenceContext(Object entity);
}
