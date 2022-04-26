package com.movie.api.controller;

import com.movie.api.model.Entities;
import com.movie.api.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/film")
public class FilmApiController {

    @Autowired
    FilmRepository filmRepository;

    @GetMapping(path = "getAll")
    public List<Entities.Film> getFilms() {
        return filmRepository.findAll();
    }

    @PostMapping(path = "save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveFilm(@RequestBody Entities.Film film) throws IllegalStateException {
        var actors = film.getActors();
        actors.forEach(actor -> actor.setFilm(film));
        filmRepository.save(film);
    }

    @GetMapping(path = "{filmId}")
    public Entities.Film getFilmById(@PathVariable("filmId") Long filmId) throws Exception {
        return filmRepository.findById(filmId).orElseThrow(() -> new Exception("Film with id <filmId> not found".replace("<filmId>", Long.toString(filmId))));
    }
}
