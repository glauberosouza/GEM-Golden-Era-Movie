package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.contract.MovieRepository;
import com.movie.golden.domain.entity.Movie;
import com.movie.golden.web.request.MovieRequest;
import com.movie.golden.web.response.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private final MovieDAO movieDAO;
    private final MoviePaginatedRepository moviePaginatedRepository;

    @Autowired
    public MovieRepositoryImpl(MovieDAO movieDAO, MoviePaginatedRepository moviePaginatedRepository) {
        this.movieDAO = movieDAO;
        this.moviePaginatedRepository = moviePaginatedRepository;
    }

    @Override
    public void save(Movie movie) {
        var movieEntity = MovieEntity.from(movie);
        movieDAO.save(movieEntity);
    }

    @Override
    public Movie findById(Long id) {
        var movieById = movieDAO.findById(id);
        if (movieById.isEmpty()) {
            throw new NoSuchElementException("Não foi localizado um filme com o id: " + id + " informado");
        }
        var movieEntity = movieById.get();
        return Movie.from(
                movieEntity.getId(),
                movieEntity.getName(),
                movieEntity.getDescription(),
                movieEntity.getGenre());
    }

    @Override
    public MovieResponse update(MovieRequest movieRequest, Long movieId) {
        var movieById = movieDAO.findById(movieId);
        if (movieById.isEmpty()) {
            throw new NoSuchElementException("Não foi localizado um filme com o id: " + movieId + " informado");
        }
        var movieEntity = movieById.get();
        movieEntity.setName(movieRequest.name());
        movieEntity.setDescription(movieRequest.description());
        movieEntity.setGenre(movieRequest.genre());
        return MovieResponse.from(movieEntity);
    }

    @Override
    public void delete(Long id) {
        if (movieDAO.findById(id).isEmpty()){
            throw new NoSuchElementException("Não foi localizado um filme com o id: " + id + " informado");
        }
        movieDAO.deleteById(id);
    }

    public Page<MovieEntity> findAll(PageRequest pageRequest) {
        return moviePaginatedRepository.findAll(pageRequest);
    }


}