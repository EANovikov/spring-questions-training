package com.xevgnov.entity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xevgnov.entity.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, UUID> {

}
