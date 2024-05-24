package br.mackenzie.restapi.medias.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.restapi.medias.entity.Serie;

public interface SerieRepository extends JpaRepository<Serie, UUID> {
   
}