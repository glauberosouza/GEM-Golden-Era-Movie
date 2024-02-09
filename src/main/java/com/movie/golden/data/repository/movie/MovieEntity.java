package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.entity.Movie;
import jakarta.persistence.*;

@Entity
@Table(name = "MOVIE")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "GENRE")
    private String genre;

    public static MovieEntity from(Movie movie) {
        var movieEntity = new MovieEntity();
        movieEntity.id = movie.getId();
        movieEntity.name = movie.getName();
        movieEntity.description = movie.getDescription();
        movieEntity.genre = movie.getGenre();
        return movieEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}