package com.xevgnov.n.plus.one.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.xevgnov.n.plus.one.entity.Author;
import com.xevgnov.n.plus.one.repository.AuthorRepository;

@Service
public class BookStoreServiceImpl implements BookStroreService {

    private final AuthorRepository authorRepository;

    public BookStoreServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
     
    @Override
    public Set<Author> getAuthors(){
        //todo
    }
    
}
