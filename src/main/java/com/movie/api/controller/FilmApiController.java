package com.movie.api.controller;


import com.movie.api.model.Entities;
import com.movie.api.services.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/film")
public class FilmApiController {

    @Autowired
    FilmService filmService;

    @Operation(summary = "it shows all films and their actors")
    @GetMapping(path = "getAll")
    public List<Entities.Film> getFilms() {
        return filmService.findAll();
    }

    @Operation(summary = "allows to register a new film")
    @PostMapping(path = "save")
    @ResponseStatus(HttpStatus.CREATED)
    public Entities.Film saveFilm(@RequestBody Entities.Film film) {
        return filmService.saveFilm(film);
    }

    @Operation(summary = "Lookup film by Id")
    @GetMapping(path = "{filmId}")
    public Entities.Film getFilmById(@Parameter(description = "a film id number", example = "12") @PathVariable("filmId") Long filmId) throws IllegalStateException {
        return filmService.findById(filmId);
    }

    @Operation(summary = "delete film by id")
    @DeleteMapping(path = "{filmId}")
    public void removeFilmById(@Parameter(description = "film id number", example = "12") @PathVariable("filmId") Long filmId) {
        filmService.deleteFilmById(filmId);
    }

}
