package com.movie.golden.templates.movieTemplate;

public class MovieRequestTemplate {
    public static MovieRequest creation(){
       return new MovieRequest(
               "Harry Potter",
               "Filme de ficção sobre bruxos",
               "Fantasia");
    }
    public static MovieRequest nullMovie(){
        return new MovieRequest(
                "",
                "Filme de ficção sobre bruxos",
                "");
    }
    public static MovieRequest update(MovieRequest movieRequest){
        return new MovieRequest(
                "Potter Harry",
                "Filme de romance",
                "Comédia");
    }
}
