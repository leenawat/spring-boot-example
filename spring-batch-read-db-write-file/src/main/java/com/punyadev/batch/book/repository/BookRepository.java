package com.punyadev.batch.book.repository;

import com.punyadev.batch.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);
    Page<Book> findByCategory(String category, Pageable pageable);
}
