package com.xevgnov.n.plus.one.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xevgnov.n.plus.one.entity.Author;

@RestController
public class BookStoreController {

    @GetMapping
    public List<Author> getAuthorsBooks() {

    }

}
