package io.github.bolivaar16.radarbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.bolivaar16.radarbook.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}