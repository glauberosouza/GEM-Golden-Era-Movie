package com.movie.golden.data.repository.movie;

import com.movie.golden.domain.entity.Movie;
import com.movie.golden.templates.movieTemplate.MovieRequestTemplate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieRepositoryImplTest {

    private static final String INSERT_INTO_MOVIE = "classpath:db.sql/insert_into_movie.sql";


    @Mock
    private MovieDAO movieDAO;
    @InjectMocks
    private MovieRepositoryImpl movieRepository;

    @BeforeAll
    static void movieSetup() {
        var creation = MovieRequestTemplate.creation();
        var movie = Movie.from(
                creation.getName(),
                creation.getDescription(),
                creation.getGenre());
        movie.setId(1L);
    }

    @DisplayName("Deve salvar um filme com sucesso")
    @Test
    void shouldSaveAMovie() {
        //GIVEN
        var creation = MovieRequestTemplate.creation();
        var movie = Movie.from(
                creation.getName(),
                creation.getDescription(),
                creation.getGenre());
        //WHEN
        movieRepository.save(movie);
        //THEN
        assertEquals("Harry Potter", movie.getName());
    }

    @DisplayName("Não deve salvar um filme com sucesso caso algum campo for nulo")
    @Test
    void shouldNotSaveAMovieWithNull() {
        //GIVEN
        var creation = MovieRequestTemplate.nullMovie();
        var movie = Movie.from(
                creation.getName(),
                creation.getDescription(),
                creation.getGenre());
        //WHEN

        //THEN
        assertThrows(IllegalArgumentException.class, () -> movieRepository.save(movie));
    }

    @DisplayName("Deve encontrar um filme pelo seu id")
    @Test
    void shouldFindAMovieById() {
        // GIVEN
        var movieEntity = new MovieEntity();
        movieEntity.setId(1L);
        movieEntity.setName("Harry Potter");
        // WHEN
        when(movieDAO.findById(anyLong())).thenReturn(Optional.of(movieEntity));
        Movie movieById = movieRepository.findById(1L);
        // THEN
        assertEquals("Harry Potter", movieById.getName());
    }
    @DisplayName("Deve retornar uma mensagem amigavel quando não encontrar um filme pelo seu id")
    @Test
    void shouldReturnAMessageIfEmpty() {
        // GIVEN
        Long id = 1L;
        // WHEN
        // THEN
        assertThrows(NoSuchElementException.class, () -> movieRepository.findById(1L));
    }

}