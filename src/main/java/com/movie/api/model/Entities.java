package com.movie.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.UtilityClass;

import javax.persistence.*;
import java.util.List;

@UtilityClass
public class Entities {

    @Data
    @Entity
    @Table(name = "Films")
    public static class Film {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String title;
        String description;
        @OneToMany(mappedBy = "film", fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
        List<Actor> actors;

    }

    @Data
    @Entity
    @Table(name = "Actors")
    public static class Actor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String name;
        String lastName;
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "film_id")
        @JsonIgnore
        Film film;
    }


}
