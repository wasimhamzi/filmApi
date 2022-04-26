package com.movie.api.repository;

import com.movie.api.model.Entities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FilmRepository extends JpaRepository<Entities.Film, Long> {

}
