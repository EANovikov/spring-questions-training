package com.xevgnov.n.plus.one.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xevgnov.n.plus.one.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

}
