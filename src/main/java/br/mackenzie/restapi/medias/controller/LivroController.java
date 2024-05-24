package br.mackenzie.restapi.medias.controller;

import java.util.*;

import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.restapi.medias.entity.Livro;
import br.mackenzie.restapi.medias.repository.LivroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class LivroController {
  @Autowired
  private LivroRepository repository;

  @GetMapping("/livros")
  public List<Livro> getLivros() {
    return repository.findAll();
  }

  // @GetMapping("/livros/procurar")
  // public List<Livro> getlivrosFromTime(@RequestParam String livro) {
  //   return repository.findByTituloIgnoreCaseOrAutorIgnoreCase(livro, livro);
  // }

  @GetMapping("/livros/{id}")
  public ResponseEntity<Livro> getLivroById(@PathVariable UUID id) {
    Optional<Livro> livro = repository.findById(id);
    if (livro.isPresent()) {
      return ResponseEntity.ok(livro.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // @PostMapping("/livros")
  // public Livro postNewLivro(@RequestBody Livro livroBody) {
  //   Livro novoLivro = new Livro(livroBody.getTitulo(), livroBody.getAutor(), livroBody.getPais(), livroBody.getEditora(), livroBody.getAno(), livroBody.getDescricao());
  //   getLivros().add(novoLivro);
  //   return repository.save(novoLivro);
  // }

  @PostMapping("/livros")
  // @PreAuthorize("hasRole('ADMIN')")
  public Livro postLivro(@RequestBody Livro livro) {
    return repository.save(livro);
  }
  

  @PutMapping("/livros/{id}")
  Optional<Livro> updateLivro(@RequestBody Livro livroBody, @PathVariable UUID id) {
    Optional<Livro> opt = repository.findById(id);

    if (opt.isPresent()) {
      Livro livro = opt.get();
      opt.get().setTitulo(livroBody.getTitulo());
      livro.setAutor(livroBody.getAutor());
      livro.setPais(livroBody.getPais());
      livro.setEditora(livroBody.getEditora());
      repository.save(livro);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return opt;
  }

  @DeleteMapping("/livros/{id}")
  void deleteLivro(@PathVariable UUID id) {
    Optional<Livro> opt = repository.findById(id);
    if(opt.isPresent()){ 
      repository.deleteById(id);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   
  }

}
