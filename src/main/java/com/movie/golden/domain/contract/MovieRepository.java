package com.movie.golden.domain.contract;

import com.movie.golden.domain.entity.Movie;
import org.springframework.stereotype.Repository;


public interface MovieRepository  {
    void save(Movie movie);
}
