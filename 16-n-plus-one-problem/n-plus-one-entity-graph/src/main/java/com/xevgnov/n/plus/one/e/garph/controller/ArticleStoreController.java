package com.xevgnov.n.plus.one.e.garph.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.n.plus.one.e.garph.dto.AuthorDto;
import com.xevgnov.n.plus.one.e.garph.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class ArticleStoreController {

    private final AuthorService authorService;

    public ArticleStoreController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable UUID id) {
        return authorService.getAuthor(id);
    }

    @GetMapping
    public List<AuthorDto> getAuthors() {
        return authorService.getAuthors();
    }

}
