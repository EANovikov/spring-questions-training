package com.xevgnov.n.plus.one.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthor(UUID id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find an author with ID=" + id));
    }

    @Override
    public Set<Author> getAuthors() {
        return new HashSet<>(authorRepository.findAll());
    }

}
