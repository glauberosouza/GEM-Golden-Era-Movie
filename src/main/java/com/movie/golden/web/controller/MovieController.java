package com.movie.golden.web.controller;

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

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody @Valid MovieRequest movieRequest) {
        var movie = Movie.from(movieRequest.name(), movieRequest.description(), movieRequest.genre());
        var movieResponse = MovieResponse.from(movie.getId(), movie.getName(), movie.getDescription(), movie.getGenre());
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }
}
