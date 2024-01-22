package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.entity.Movie;
import jakarta.persistence.*;

@Entity
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String genre;

    public static MovieEntity from(Movie movie) {
        var movieEntity = new MovieEntity();
        movieEntity.id = movie.getId();
        movieEntity.name = movie.getName();
        movieEntity.description = movieEntity.description;
        movieEntity.genre = movie.getGenre();
        return movieEntity;
    }
}