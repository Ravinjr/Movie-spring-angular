package com.example.backend.repo;

import com.example.backend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MovieRepo extends JpaRepository<Movie,Long> {
    Movie findByImdb(String imdb);
    // boolean existByImdb(String imdb);

     boolean existsByImdb(String imdb);
     void deleteByImdb(String imdb);

    Optional<Movie> getByImdb(String imdb);
}
