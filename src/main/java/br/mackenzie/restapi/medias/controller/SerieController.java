package br.mackenzie.restapi.medias.controller;

import java.util.*;

import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.restapi.medias.entity.Serie;
import br.mackenzie.restapi.medias.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class SerieController {
  @Autowired
  private SerieRepository repository;

  @GetMapping("/series")
  public List<Serie> getSeries() {
    return repository.findAll();
  }

  // @GetMapping("/series/procurar")
  // public List<Serie> getseriesFromTime(@RequestParam String serie) {
  //   return repository.findByTituloIgnoreCaseOrAutorIgnoreCase(serie, serie);
  // }

  @GetMapping("/series/{id}")
  public ResponseEntity<Serie> getSerieById(@PathVariable UUID id) {
    Optional<Serie> serie = repository.findById(id);
    if (serie.isPresent()) {
      return ResponseEntity.ok(serie.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // @PostMapping("/series")
  // public Serie postNewSerie(@RequestBody Serie serieBody) {
  //   Serie novaSerie = new Serie(serieBody.getTitulo(), serieBody.getAutor(), serieBody.getPais(), serieBody.getEditora(), serieBody.getAno(), serieBody.getDescricao());
  //   getSeries().add(novaSerie);
  //   return repository.save(novaSerie);
  // }

  @PostMapping("/series")
  public Serie postSerie(@RequestBody Serie serie) {
    return repository.save(serie);
  }

  @PutMapping("/series/{id}")
  Optional<Serie> updateSerie(@RequestBody Serie serieBody, @PathVariable UUID id) {
    Optional<Serie> opt = repository.findById(id);

    if (opt.isPresent()) {
      Serie serie = opt.get();
      opt.get().setTitulo(serieBody.getTitulo());
      serie.setAutor(serieBody.getAutor());
      serie.setPais(serieBody.getPais());
      serie.setEditora(serieBody.getEditora());
      repository.save(serie);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return opt;
  }

  @DeleteMapping("/series/{id}")
  void deleteSerie(@PathVariable UUID id) {
    Optional<Serie> opt = repository.findById(id);
    if(opt.isPresent()){ 
      repository.deleteById(id);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   
  }

}
