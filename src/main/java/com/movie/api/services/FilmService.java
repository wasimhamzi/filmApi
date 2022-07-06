package com.movie.api.services;

import com.movie.api.model.Entities;
import com.movie.api.repository.FilmRepository;
import com.movie.api.validator.FilmValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    public List<Entities.Film> findAll() {
        return filmRepository.findAll();
    }

    public Entities.Film saveFilm(Entities.Film film) throws IllegalArgumentException {
        var actors = film.getActors();
        actors.forEach(actor -> actor.setFilm(film));
        final var filmValidator = FilmValidator.isTitleLengthOk().and(FilmValidator.isActorsListEmpty()).apply(film);
        if (!filmValidator.isEmpty()) {
            throw new IllegalArgumentException(filmValidator);
        }
        return filmRepository.save(film);
    }

    public Entities.Film findById(final Long id) throws IllegalStateException {
        return filmRepository.findById(id).orElseThrow(() -> new IllegalStateException("Film with id <filmId> not found".replace("<filmId>", Long.toString(id))));
    }

    public void deleteFilmById(final Long id) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        } else {
            throw new IllegalStateException("cant delete Film with id <filmId> not found".replace("<filmId>", Long.toString(id)));
        }
    }

}
