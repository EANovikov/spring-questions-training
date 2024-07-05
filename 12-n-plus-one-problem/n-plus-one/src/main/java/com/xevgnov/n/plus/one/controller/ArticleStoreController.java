package com.xevgnov.n.plus.one.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.service.AuthorService;

@RestController
public class ArticleStoreController {

    private AuthorService authorService;
    @GetMapping
    public Set<Author> getAuthorsBooks() {
       return authorService.getAuthors();
    }

}
