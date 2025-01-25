package com.xevgnov.proxy.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xevgnov.proxy.dto.ArticleDto;
import com.xevgnov.proxy.service.ArticleService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ArticleDto get(@PathVariable UUID id) {
        return articleService.get(id);
    }

    @GetMapping
    public List<ArticleDto> getAll() {
        return articleService.getAll();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid ArticleDto order) {
        ArticleDto article = articleService.create(order);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}").buildAndExpand(article.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody ArticleDto order, @PathVariable UUID id) {
        articleService.update(order, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        articleService.delete(id);
    }

}
