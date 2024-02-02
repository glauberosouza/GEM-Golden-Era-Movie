package com.movie.golden.web.controller;

import com.movie.golden.data.repository.movie.MovieEntity;
import com.movie.golden.data.repository.movie.MovieRepositoryImpl;
import com.movie.golden.domain.entity.Movie;
import com.movie.golden.web.request.MovieRequest;
import com.movie.golden.web.response.MovieResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ResponseEntity<MovieResponse> findMovie(@PathVariable Long id) {
        var movieById = movieRepository.findById(id);
        var movieEntity = MovieEntity.from(movieById);
        var movieResponse = MovieResponse.from(movieEntity);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponse);
    }

    @GetMapping("/page/{number}")
    public ResponseEntity<Page<MovieEntity>> allProductsPaginated(@PathVariable Integer number) {
        var page = PageRequest.of(number, 5);
        var moviePaginated = movieRepository.findAll(page);
        return ResponseEntity.status(HttpStatus.OK).body(moviePaginated);
    }

    @PutMapping("{movieId}")
    public ResponseEntity<MovieResponse> update(@RequestBody MovieRequest movieRequest, @PathVariable Long movieId) {
        var movieById = movieRepository.findById(movieId);
        var movieResponse = movieRepository.update(movieRequest, movieId);
        return ResponseEntity.status(HttpStatus.OK).body(movieResponse);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieRepository.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
