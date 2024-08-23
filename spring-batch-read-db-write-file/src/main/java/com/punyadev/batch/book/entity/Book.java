package com.punyadev.batch.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String category;

    // Getters and Setters
}
