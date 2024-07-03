package com.xevgnov.n.plus.one.service;

import org.springframework.stereotype.Service;

import com.xevgnov.n.plus.one.repository.AuthorRepository;

@Service
public class BookStoreServiceImpl {

    private final AuthorRepository authorRepository;

    public BookStoreServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    
}
