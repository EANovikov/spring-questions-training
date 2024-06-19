package com.xevgnov.proxy.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xevgnov.proxy.entity.Article;

@Repository
public interface ArticleRepository  extends JpaRepository<Article,UUID> {

}
