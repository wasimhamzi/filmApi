package com.movie.api.validator;

import com.movie.api.model.Entities;

import java.util.function.Function;

public interface FilmValidator extends Function<Entities.Film, String> {


    static FilmValidator isTitleLengthOk() {
        return film -> film.getTitle().length() < 3 ? "TITLE_LENGTH_SHOULD_BE_OF_LENGTH_OF_3_AT_LEATS" : "";
    }

    static FilmValidator isActorsListEmpty() {
        return film -> film.getActors().isEmpty() ? "ACTORS_LIST_CANT_BE_EMPTY" : "";
    }

    default FilmValidator and(FilmValidator other) {
        return film -> {
            final var result = this.apply(film);
            return !result.isEmpty() ? result.concat(",").concat(other.apply(film)) : result.concat(other.apply(film));
        };
    }


}
