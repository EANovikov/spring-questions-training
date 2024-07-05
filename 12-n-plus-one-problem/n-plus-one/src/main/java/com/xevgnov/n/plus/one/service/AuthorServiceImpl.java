package com.xevgnov.n.plus.one.service;

import java.util.HashSet;
import java.util.Set;

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
    public Set<Author> getAuthors(){
        return new HashSet<>(authorRepository.findAll());
    }
    
}
