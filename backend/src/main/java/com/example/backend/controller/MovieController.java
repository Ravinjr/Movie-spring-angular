package com.example.backend.controller;

import com.example.backend.dto.ExceptionDTO;
import com.example.backend.dto.MovieDTO;
import com.example.backend.model.Movie;
import com.example.backend.repo.MovieRepo;
import com.example.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepo movieRepo;

    @PostMapping("/movies")
    public ExceptionDTO addUser(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

    @GetMapping("/movies")
    public ExceptionDTO getMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/moviesfind")
    public Movie findMovie(@RequestParam("imdb") String imdb){return movieRepo.findByImdb(imdb);}

    @PutMapping("/movies")
    public ExceptionDTO updateMovie(@RequestBody MovieDTO movieDTO){return movieService.updateMovie(movieDTO);}

    @DeleteMapping("/movies/{imdb}")
    public ExceptionDTO deleteMovie(@PathVariable String imdb){
        return movieService.deleteMovie(imdb);
    }
}
