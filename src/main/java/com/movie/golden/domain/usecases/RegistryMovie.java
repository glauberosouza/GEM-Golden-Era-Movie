package com.movie.golden.domain.usecases;

import com.movie.golden.domain.contract.MovieRepository;
import com.movie.golden.domain.contract.RegistryMovieBorder;
import com.movie.golden.domain.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistryMovie implements RegistryMovieBorder {

    private final MovieRepository movieRepository;
    @Autowired
    public RegistryMovie(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void save(Movie movie) {
        movieRepository.save(movie);
    }
}
