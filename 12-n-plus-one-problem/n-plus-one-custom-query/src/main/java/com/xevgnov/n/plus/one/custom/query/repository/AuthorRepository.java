package com.xevgnov.n.plus.one.custom.query.repository;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xevgnov.n.plus.one.custom.query.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>{

    @Query("select aut from Author aut left join fetch aut.articles art")
    List<Author> findAllAuthors();
}
