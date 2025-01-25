package com.xevgnov.n.plus.one.service;

import java.util.List;
import java.util.UUID;

import com.xevgnov.n.plus.one.dto.AuthorDto;

public interface AuthorService {

    AuthorDto getAuthor(UUID id);
    List<AuthorDto> getAuthors();
  
}
