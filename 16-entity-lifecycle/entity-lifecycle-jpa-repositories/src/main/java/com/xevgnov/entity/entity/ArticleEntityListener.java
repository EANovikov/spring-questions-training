package com.xevgnov.entity.entity;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticleEntityListener {

    @PrePersist
    private void beforeCreate(Article article) {
            log.info("Creating article: " + article.getId());
    }

    @PreUpdate
    private void beforeUpdate(Article article) {
        log.info("Updating article: " + article.getId());
    }

    @PreRemove
    private void beforeDelete(Article article) {
        log.info("Removing article: " + article.getId());
    }

    @PostPersist
    private void afterCreate(Article article) {
            log.info("Creating article completed: " + article.getId());
    }

    @PostUpdate
    private void afterUpdate(Article article) {
        log.info("Updating article completed: " + article.getId());
    }

    @PostRemove
    private void afterDelete(Article article) {
        log.info("Removing article completed: " + article.getId());
    }

    
    @PostLoad
    private void afterLoad(Article article) {
        log.info("Article loaded from database: " + article.getId());
    }

}
