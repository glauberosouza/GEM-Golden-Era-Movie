package com.movie.golden.templates.movieTemplate;

public class MovieRequest {
    private String name;
    private String description;
    private String gender;

    public MovieRequest() {
    }

    public MovieRequest(String name, String description, String gender) {
        this.name = name;
        this.description = description;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
