package com.xevgnov.n.plus.one.e.garph.service;

import java.util.List;
import java.util.UUID;

import com.xevgnov.n.plus.one.e.garph.dto.AuthorDto;

public interface AuthorService {

    AuthorDto getAuthor(UUID id);
    List<AuthorDto> getAuthors();
  
}
