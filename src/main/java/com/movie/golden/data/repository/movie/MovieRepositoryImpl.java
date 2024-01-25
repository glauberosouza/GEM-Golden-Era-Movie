package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.contract.MovieRepository;
import com.movie.golden.domain.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public Movie findById(Long id) {
        Optional<MovieEntity> movieById = movieDAO.findById(id);
        if (movieById.isEmpty()){
            throw new NoSuchElementException("Não foi localizado um filme com o id: " + id + " informado");
        }
        MovieEntity movieEntity = movieById.get();
        return Movie.from(
                movieEntity.getId(),
                movieEntity.getName(),
                movieEntity.getDescription(),
                movieEntity.getGenre());
    }

}
