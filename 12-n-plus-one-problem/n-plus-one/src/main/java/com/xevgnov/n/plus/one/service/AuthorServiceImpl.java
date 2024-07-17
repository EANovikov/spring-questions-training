package com.xevgnov.n.plus.one.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xevgnov.n.plus.one.dto.ArticleDto;
import com.xevgnov.n.plus.one.dto.AuthorDto;
import com.xevgnov.n.plus.one.entity.Article;
import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.repository.AuthorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto getAuthor(UUID id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find an author with ID=" + id));
        AuthorDto authorDto = mapAutor(author);        
        log.info("Found the author: {}", authorDto);
        return authorDto;
    }

    @Override
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll()
        .stream()
        .map(author-> mapAutor(author))
        .toList();
    }

    AuthorDto mapAutor(Author author){
       return AuthorDto.builder()
            .id(author.getId())
            .name(author.getName())
            .surname(author.getSurname())
            .articles(author.getArticles().stream().map(article->mapArticle(article)).toList())
            .build();
    }

    ArticleDto mapArticle(Article article){
        return ArticleDto.builder()
        .id(article.getId())
        .title(article.getTitle())
        .text(article.getText())
        .created(article.getCreated())
        .updated(article.getUpdated())
     //   .author(mapAutor(article.getAuthor()))
        .build();
    }
}
