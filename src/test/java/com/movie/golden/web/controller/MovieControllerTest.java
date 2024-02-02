package com.movie.golden.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.golden.BaseCompTest;
import com.movie.golden.data.repository.movie.MovieDAO;
import com.movie.golden.data.repository.movie.MoviePaginatedRepository;
import com.movie.golden.data.repository.movie.MovieRepositoryImpl;
import com.movie.golden.templates.movieTemplate.MovieRequestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends BaseCompTest {

    private static final String INSERT_INTO_MOVIE = "classpath:db.sql/insert_into_movie.sql";


    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieRepositoryImpl movieRepository;
    @Autowired
    private MovieDAO movieDAO;
    @Autowired
    private MoviePaginatedRepository moviePaginatedRepository;

    @Test
    @DisplayName("Deve cadastrar um filme com sucesso")
    public void shouldSaveANewMovie() throws Exception {
        //GIVEN
        var movie = MovieRequestTemplate.creation();
        var body = objectMapper.writeValueAsString(movie);
        //WHEN
        mvc.perform(post("/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated());
        //THEN
    }

    @Test
    @DisplayName("Não deve cadastrar um filme caso os campos forem nulos")
    public void shouldNotSaveAMovieIfFieldsAreNull() throws Exception {
        //GIVEN
        var movie = MovieRequestTemplate.nullMovie();
        var body = objectMapper.writeValueAsString(movie);
        //WHEN
        mvc.perform(post("/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is4xxClientError());
        //THEN
    }

    @Test
    @DisplayName("Deve encontrar um filme pelo id")
    @Sql(INSERT_INTO_MOVIE)
    public void shouldFindAMovieById() throws Exception {
        //GIVEN
        //WHEN
        mvc.perform(get("/v1/movies/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is("Harry Potter")));
        //THEN
    }

    @Test
    @DisplayName("Deve retornar todos os filmes cadastrados páginados")
    public void shouldReturnAllMovies() throws Exception {
        //GIVEN
        //WHEN
        mvc.perform(get("/v1/movies/" + "/page/0")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        //THEN
    }

    @Test
    @DisplayName("Deve localizar um filme pelo id e atualiza-lo")
    @Sql(INSERT_INTO_MOVIE)
    public void shouldUpdateAMovie() throws Exception {
        //GIVEN
        var movie = MovieRequestTemplate.creation();
        var movieUpdated = MovieRequestTemplate.update(movie);
        var body = objectMapper.writeValueAsString(movie);
        //WHEN
        mvc.perform(put("/v1/movies/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().is2xxSuccessful());
        //THEN
        Assertions.assertEquals("Potter Harry", movieUpdated.getName());
    }

    @Test
    @DisplayName("Deve deletar um filme pelo seu id")
    @Sql(INSERT_INTO_MOVIE)
    public void shouldDeleteAMovie() throws Exception {
        //GIVEN
        //WHEN
        mvc.perform(delete("/v1/movies/" + 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        //THEN
        Assertions.assertTrue(movieDAO.findById(1L).isEmpty(), "O filme não deve estar mais no repositório.");
    }
}