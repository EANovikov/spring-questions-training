package com.xevgnov.n.plus.one.entity;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Value;

@Entity
@Table(name = "AUTHOR")
@Value
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY,
        //mappedBy = "AUTHOR",
    cascade = CascadeType.ALL)
   // @JoinColumn(name = "book_id")
    private Set<Article> books;
}
