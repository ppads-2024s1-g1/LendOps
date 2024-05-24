package br.mackenzie.restapi.medias.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mackenzie.restapi.medias.entity.Livro;


public interface LivroRepository extends JpaRepository<Livro, UUID> {
   
}