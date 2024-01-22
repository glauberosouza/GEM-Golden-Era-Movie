package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.contract.MovieRepository;
import com.movie.golden.domain.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private MovieDAO movieDAO;

    @Autowired
    public MovieRepositoryImpl(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public void save(Movie movie) {
        var movieEntity = MovieEntity.from(movie);
        movieDAO.save(movieEntity);
    }
}
