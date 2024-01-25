package com.movie.golden.domain.contract;

import com.movie.golden.domain.entity.Movie;


public interface MovieRepository  {
    void save(Movie movie);
    Movie findById(Long id);
}
