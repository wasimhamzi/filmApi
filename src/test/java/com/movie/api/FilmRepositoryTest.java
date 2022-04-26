package com.movie.api;


import com.movie.api.model.Entities;
import com.movie.api.repository.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

@DataJpaTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @AfterEach
    void deleteAll(){
        filmRepository.deleteAll();
    }


    @Test
    void check_if_film_exists(){
        //Given
        //Given
        var actor1 = new Entities.Actor();
        actor1.setName("Harrison");
        actor1.setLastName("Ford");

        var actor2 = new Entities.Actor();
        actor2.setName("Mark");
        actor2.setLastName("Hamill");


        var actors = Arrays.asList(actor1, actor2);

        var film = new Entities.Film();
        film.setTitle("Star Wars: The Empire Strikes Back");
        film.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
        film.setActors(actors);

        actor1.setFilm(film);
        actor2.setFilm(film);

        filmRepository.save(film);

        //When

        var expected = filmRepository.findById(1L);

        //Then

        Assertions.assertThat(film.getTitle()).isEqualTo(expected.get().getTitle());
    }
}
