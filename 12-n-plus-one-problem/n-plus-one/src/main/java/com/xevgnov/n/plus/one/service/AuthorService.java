package com.xevgnov.n.plus.one.service;

import java.util.Set;
import java.util.UUID;

import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.entity.Article;

public interface AuthorService {

    Author getAuthor(UUID id);
    Set<Author> getAuthors();
  
}
