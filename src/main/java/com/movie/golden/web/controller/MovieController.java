package com.movie.golden.web.controller;

import com.movie.golden.data.repository.movie.MovieRepositoryImpl;
import com.movie.golden.domain.entity.Movie;
import com.movie.golden.web.request.MovieRequest;
import com.movie.golden.web.response.MovieResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    private final MovieRepositoryImpl movieRepository;

    @Autowired
    public MovieController(MovieRepositoryImpl movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody @Valid MovieRequest movieRequest) {
        var movie = Movie.from(movieRequest.name(), movieRequest.description(), movieRequest.genre());
        var movieResponse = MovieResponse.from(movie.getId(), movie.getName(), movie.getDescription(), movie.getGenre());
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }
}
