package com.movie.api;

import com.movie.api.model.Entities;
import com.movie.api.validator.FilmValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FilmValidatorTest {

    @Test
    public void is_film_title_ok() {
        //Given
        var film = new Entities.Film();
        film.setId(1L);
        film.setTitle("Star Wars: The Empire Strikes Back");
        film.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
        //When
        final var filmValidator = FilmValidator.isTitleLengthOk().apply(film);
        //Then
        Assertions.assertThat(filmValidator).isEqualTo("");
    }


    @Test
    public void is_film_title_ko() {
        //Given
        var film = new Entities.Film();
        film.setId(1L);
        film.setTitle("St");
        film.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
        //When
        final var filmValidator = FilmValidator.isTitleLengthOk().apply(film);
        //Then
        Assertions.assertThat(filmValidator).isEqualTo("TITLE_LENGTH_SHOULD_BE_OF_LENGTH_OF_3_AT_LEATS");
    }

    @Test
    public void check_actors_list_is_empty() {
        //Given
        var film = new Entities.Film();
        film.setActors(new ArrayList<>());
        //When
        final var filmValidator = FilmValidator.isActorsListEmpty().apply(film);
        //Then
        Assertions.assertThat(filmValidator).isEqualTo("ACTORS_LIST_CANT_BE_EMPTY");
    }

    @Test
    public void check_actors_list_not_empty() {
        //Given
        var film = new Entities.Film();
        var actor1 = new Entities.Actor();
        actor1.setName("Harrison");
        actor1.setLastName("Ford");

        var actor2 = new Entities.Actor();
        actor2.setName("Mark");
        actor2.setLastName("Hamill");

        film.setActors(Arrays.asList(actor1,actor2));
        //When
        final var filmValidator = FilmValidator.isActorsListEmpty().apply(film);
        //Then
        Assertions.assertThat(filmValidator).isEqualTo("");
    }
}
