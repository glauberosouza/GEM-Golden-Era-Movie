package com.movie.golden.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.golden.BaseCompTest;
import com.movie.golden.data.repository.movie.MovieRepositoryImpl;
import com.movie.golden.templates.movieTemplate.MovieRequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends BaseCompTest {



    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieRepositoryImpl movieRepository;

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

    //TODO: Deve cadastrar um filme com sucesso
    //TODO: Não deve cadastrar um filme caso os campos estejam nulos
    //TODO: Deve encontrar um filme pelo id
    //TODO: Deve retornar uma mensagem amigavel caso não encontre o filme pelo id
    //TODO: Deve retornar todos os filmes cadastrados
    //TODO: Deve editar o filme escolhido pelo Id.
    //TODO: Deve deletar um filme pelo seu id.

}