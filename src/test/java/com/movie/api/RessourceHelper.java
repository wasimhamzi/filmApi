package com.movie.api;

import com.movie.api.model.Entities;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class RessourceHelper {

    public static Entities.Film getCommunFilm() {
        var actor1 = new Entities.Actor();
        actor1.setName("Harrison");
        actor1.setLastName("Ford");

        var actor2 = new Entities.Actor();
        actor2.setName("Mark");
        actor2.setLastName("Hamill");


        var actors = Arrays.asList(actor1, actor2);

        var film = new Entities.Film();
        film.setId(1L);
        film.setTitle("Star Wars: The Empire Strikes Back");
        film.setDescription("Darth Vader is adamant about turning Luke Skywalker to the dark side.");
        film.setActors(actors);

        actor1.setFilm(film);
        actor2.setFilm(film);

        return film;
    }
}
