package com.movie.api;


import com.movie.api.repository.FilmRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class FilmRepositoryTest {

    @Autowired
    private FilmRepository filmRepository;

    @AfterEach
    void deleteAll() {
        filmRepository.deleteAll();
    }


    @Test
    void check_if_film_exists() {
        //Given
        var film = RessourceHelper.getCommunFilm();
        filmRepository.save(film);
        //When
        var expected = filmRepository.findById(1L);
        //Then
        Assertions.assertThat(film.getTitle()).isEqualTo(expected.get().getTitle());
    }
}
