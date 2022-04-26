package com.movie.api;


import com.movie.api.model.Entities;
import com.movie.api.repository.FilmRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@OpenAPIDefinition
public class MovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner insertFilmstoDb(FilmRepository filmRepository) {

        return args -> {

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
        };
    }
}
