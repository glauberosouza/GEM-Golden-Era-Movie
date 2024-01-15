package com.movie.golden.templates.movieTemplate;

public class MovieRequest {
    private String name;
    private String description;
    private String genre;

    public MovieRequest() {
    }

    public MovieRequest(String name, String description, String genre) {
        this.name = name;
        this.description = description;
        this.genre = genre;
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
