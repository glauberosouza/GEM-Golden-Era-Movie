package com.movie.golden.domain.entity;

public class Movie {
    private Long id;
    private String name;
    private String description;
    private String genre;
    public Movie() {
    }

    public Movie(String name, String description, String genre) {
        this.name = name;
        this.description = description;
        this.genre = genre;
    }

    public static Movie from(String name, String description, String genre) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setGenre(genre);
        return movie;
    }
    public static Movie from(Long id, String name, String description, String genre) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setDescription(description);
        movie.setGenre(genre);
        return movie;
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
