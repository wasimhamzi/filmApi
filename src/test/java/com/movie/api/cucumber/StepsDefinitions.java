package com.movie.api.cucumber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.model.Entities;
import com.movie.api.repository.FilmRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;


public class StepsDefinitions {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Entities.Film filmExpected;

    private Entities.Film filmActual;


    @Given("^the following film$")
    public void givingTheFollowingFilm(Entities.Film film) {
        filmActual = film;
    }

    @And("^the following Actors$")
    public void givingTheFollowingActors(List<Entities.Actor> actors) {
        filmActual.setActors(actors);
    }

    @When("^the user sends a request to get film by id (.*)$")
    public void whenTheUserSendsARequest(Long id) throws JsonProcessingException {
        filmExpected = objectMapper.readValue(testRestTemplate.getForEntity("/api/film/{filmId}", String.class, id).getBody(), Entities.Film.class);
    }

    @Then("^the response will return the film$")
    public void theResponseWillReturnAFilm() {
        validateResult();
    }

    private void validateResult() {
        Assertions.assertEquals(filmExpected.getTitle(), filmActual.getTitle());
        Assertions.assertEquals(filmExpected.getActors().size(), filmActual.getActors().size());
    }

}
