package com.movie.golden.web.controller;

import com.movie.golden.BaseCompTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MovieControllerTest extends BaseCompTest {



    @Autowired
    private MockMvc mvc;
    @Autowired
    private MovieService movieService;

    @Test
    @DisplayName("Deve cadastrar um filme com sucesso")
    public void shouldSaveANewMovie(){
        //GIVEN
        var movie =
        //WHEN
        mvc.perform(post().contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status());
        //THEN
        Assertions.assertEquals();
    }

    //TODO: Deve cadastrar um filme com sucesso
    //TODO: Não deve cadastrar um filme caso os campos estejam nulos
    //TODO: Deve encontrar um filme pelo id
    //TODO: Deve retornar uma mensagem amigavel caso não encontre o filme pelo id
    //TODO: Deve retornar todos os filmes cadastrados
    //TODO: Deve editar o filme escolhido pelo Id.
    //TODO: Deve deletar um filme pelo seu id.

}