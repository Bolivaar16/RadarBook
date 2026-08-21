package io.github.bolivaar16.radarbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.bolivaar16.radarbook.model.Author;

public interface AuthorRepository extends JpaRepository<Author , Long> {
    
}
