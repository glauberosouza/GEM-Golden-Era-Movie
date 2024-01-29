package com.movie.golden.domain.contract;

import com.movie.golden.domain.entity.Movie;
import com.movie.golden.web.request.MovieRequest;
import com.movie.golden.web.response.MovieResponse;


public interface MovieRepository  {
    void save(Movie movie);
    Movie findById(Long id);

    MovieResponse update(MovieRequest movieRequest, Long movieId);
}
