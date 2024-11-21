package com.example.backend.service;

import com.example.backend.dto.ExceptionDTO;
import com.example.backend.dto.MovieDTO;
import com.example.backend.model.Movie;
import com.example.backend.repo.MovieRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ExceptionDTO addMovie(MovieDTO movieDTO) {
        try {
            if (movieRepo.existsByImdb(movieDTO.imdb)) {
                return new ExceptionDTO("04", "Movie already Exists", null);
            }
            return new ExceptionDTO("02", "No Such Movie Exists", null);
        } catch (Exception e) {
            e.printStackTrace();
            movieRepo.save(modelMapper.map(movieDTO, Movie.class));
            return new ExceptionDTO("00", "success", null);
        }


    }

    public ExceptionDTO getAllMovies() {

        try {
            List<Movie> moviesList = movieRepo.findAll();
            if (moviesList.isEmpty()) {
                return new ExceptionDTO("02", "No Movies Found", null);
            }
            return new ExceptionDTO("00", "success", modelMapper.map(moviesList, new TypeToken<List<MovieDTO>>() {
            }.getType()));


        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionDTO("02", "Error in get movies", null);
        }

    }

    public ExceptionDTO updateMovie(MovieDTO movieDTO) {
        try {
            if (movieRepo.existsByImdb(movieDTO.imdb)) {
                Optional<Movie> byImdb = movieRepo.getByImdb(movieDTO.imdb);
                byImdb.get().setCategory(movieDTO.getCategory());
                byImdb.get().setDescription(movieDTO.getDescription());
                byImdb.get().setRating(movieDTO.getRating());
                byImdb.get().setYear(movieDTO.getYear());
                byImdb.get().setTitle(movieDTO.getTitle());
                byImdb.get().setImageUrl(movieDTO.getImageUrl());
                movieRepo.save(byImdb.get());
                return new ExceptionDTO("00", "success", null);
            }
            return new ExceptionDTO("02", "No Such Movie Exists", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionDTO("02", "No Such Movie Exists", null);
        }


    }

    public ExceptionDTO deleteMovie(String imdb) {
        try {
            if (movieRepo.existsByImdb(imdb)) {
                movieRepo.deleteByImdb(imdb);
                return new ExceptionDTO("00", "success", null);
            }
            return new ExceptionDTO("02", "No Such Movie Exists", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ExceptionDTO("02", "No Such Movie Exists", null);
        }

    }


}
