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
import java.util.Objects;

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
        checkMovie(movie);
        var movieEntity = MovieEntity.from(movie);
        movieDAO.save(movieEntity);
    }

    private void checkMovie(Movie movie) {
        if (Objects.isNull(movie.getName()) || movie.getName().isBlank() ||
                Objects.isNull(movie.getDescription()) || movie.getDescription().isBlank() ||
                Objects.isNull(movie.getGender()) || movie.getGender().isBlank()) {
            throw new IllegalArgumentException("Um ou mais campos do filme estão em branco.");
        }
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
                movieEntity.getGender());
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
        movieEntity.setGender(movieRequest.gender());
        movieDAO.save(movieEntity);
        return MovieResponse.from(movieEntity);
    }

    @Override
    public void delete(Long id) {
        if (movieDAO.findById(id).isEmpty()) {
            throw new NoSuchElementException("Não foi localizado um filme com o id: " + id + " informado");
        }
        movieDAO.deleteById(id);
    }

    public Page<MovieEntity> findAll(PageRequest pageRequest) {
        return moviePaginatedRepository.findAll(pageRequest);
    }


}