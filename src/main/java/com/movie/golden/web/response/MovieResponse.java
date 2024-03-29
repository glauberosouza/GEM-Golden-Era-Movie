package com.movie.golden.web.response;

import com.movie.golden.data.repository.movie.MovieEntity;

public record MovieResponse(Long id, String name, String description, String gender) {


    public static MovieResponse from(Long id, String name, String description, String genre) {

        return new MovieResponse(id, name, description, genre);

    }
    public static MovieResponse from(MovieEntity movie) {

        return new MovieResponse(
                movie.getId(), movie.getName(), movie.getDescription(), movie.getGender()
        );
    }
}
