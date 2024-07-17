package com.xevgnov.n.plus.one.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.n.plus.one.dto.AuthorDto;
import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.service.AuthorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/authors")
public class ArticleStoreController {

    private final AuthorService authorService;

       public ArticleStoreController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthors(@PathVariable UUID id) {
       return authorService.getAuthor(id);
    }

}
