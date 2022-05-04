package com.movie.api;

import com.movie.api.repository.FilmRepository;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.movie.api.RessourceHelper.getCommunFilm;
import static java.util.Optional.of;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @MockBean
    private FilmRepository filmRepository;

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void should_get_all_films() {
        //Given

        var film = getCommunFilm();

        var films = List.of(film);
        Mockito.when(filmRepository.findAll()).thenReturn(films);

        //When and Then

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/film/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("size()", Matchers.is(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Star Wars: The Empire Strikes Back")))
                .andReturn();

    }

    @SneakyThrows
    @Test
    public void should_get_film_by_id() {
        //Given

        var film = getCommunFilm();

        Mockito.when(filmRepository.findById(ArgumentMatchers.anyLong())).thenReturn(of(film));


        //When and Then

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/film/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title", Matchers.is("Star Wars: The Empire Strikes Back")))
                .andReturn();

    }

    @SneakyThrows
    @Test
    public void should_post_film() {

        //Given

        var film = getCommunFilm();

        Mockito.when(filmRepository.save(film)).thenReturn(film);


        //When and Then

        ResultActions expect = mockMvc.perform(MockMvcRequestBuilders.post("/api/film/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\":\"Star Wars: The Empire Strikes Back\",\n" +
                        "   \"description\":\"Darth Vader is adamant about turning Luke Skywalker to the dark side.\",\n" +
                        "   \"actors\":[\n" +
                        "      {\n" +
                        "         \"nom\":\"Ford\",\n" +
                        "         \"prenom\":\"Harrison\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"nom\":\"Hamill\",\n" +
                        "         \"prenom\":\"Mark\"\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}")).andExpect(status().isCreated());


    }


}
