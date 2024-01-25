package com.movie.golden.web.response;

import com.movie.golden.data.repository.movie.MovieEntity;
import com.movie.golden.domain.entity.Movie;

public record MovieResponse(Long id, String name, String description, String genre) {


    public static MovieResponse from(Long id, String name, String description, String genre) {

        return new MovieResponse(id, name, description, genre);

    }
    public static MovieResponse from(MovieEntity movie) {

        return new MovieResponse(
                movie.getId(), movie.getName(), movie.getDescription(), movie.getGenre()
        );
    }
}
