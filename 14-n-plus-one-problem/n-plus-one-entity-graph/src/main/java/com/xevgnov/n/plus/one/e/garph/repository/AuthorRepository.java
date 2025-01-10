package com.xevgnov.n.plus.one.e.garph.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.xevgnov.n.plus.one.e.garph.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    @NonNull
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"articles"})
    List<Author> findAll();

}
