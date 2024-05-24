package br.mackenzie.restapi.medias.controller;

import java.util.*;

import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.restapi.Avaliacao.Avaliacao;
import br.mackenzie.restapi.Avaliacao.AvaliacaoRepository;
import br.mackenzie.restapi.medias.entity.Filme;
import br.mackenzie.restapi.medias.repository.FilmeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class FilmeController {  
  @Autowired
  private FilmeRepository repository;
  @Autowired 
  private AvaliacaoRepository avaliacaoRepository;
  // @Autowired
  // private

  @GetMapping("/filmes")
  public List<Filme> getFilmes() {
    return repository.findAll();
  }

  // @GetMapping("/filmes/procurar")
  // public List<Filme> getfilmesFromTime(@RequestParam String filme) {
  //   return repository.findByTituloIgnoreCaseOrAutorIgnoreCase(filme, filme);
  // }

@GetMapping("/filmes/{id}")
public ResponseEntity<Map<String, Object>> getFilmeAndAvaliacaoById(@PathVariable UUID id) {
    Optional<Filme> filmeOptional = repository.findById(id);
    List<Avaliacao> avaliacoes = avaliacaoRepository.findByItemId(id);

    if (filmeOptional.isPresent()) {
        Filme filme = filmeOptional.get();

        Map<String, Object> response = new HashMap<>();
        response.put("avaliacoes", avaliacoes);
        response.put("filme", filme);
        

        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.notFound().build();
    }
}


  // @GetMapping("/filmes/{id}")
  // public ResponseEntity<List<Avaliacao>> getFilmeAvaliacoesById(@PathVariable UUID id){
  //     Optional<Filme> filmeOptional = repository.findById(id);
  //     if (filmeOptional.isPresent()) {
  //         Filme filme = filmeOptional.get();
  //         List<Avaliacao> avaliacoes = filme.getAvaliacoes();
  //         return ResponseEntity.ok(avaliacoes);
  //     } else {
  //         return ResponseEntity.notFound().build();
  //     }
  // }
    



  // @PostMapping("/filmes")
  // public Filme postNewFilme(@RequestBody Filme filmeBody) {
  //   Filme novoFilme = new Filme(filmeBody.getTitulo(), filmeBody.getAutor(), filmeBody.getPais(), filmeBody.getEditora(), filmeBody.getAno(),filmeBody.getDescricao());
  //   getFilmes().add(novoFilme);
  //   return repository.save(novoFilme);
  // }

  @PostMapping("/filmes")
  //@PreAuthorize("hasRole('ADMIN')")
  public Filme postFilme(@RequestBody Filme filme) {
      return repository.save(filme);
  }

  @PostMapping("/filmes/{filmeId}")
  public ResponseEntity<?> saveAvaliacaoFilme(@PathVariable UUID filmeId, @RequestBody Avaliacao avaliacao) {
      Optional<Filme> filmeOptional = repository.findById(filmeId);
      if (filmeOptional.isPresent()) {
          avaliacao.setItemId(filmeId);
          avaliacaoRepository.save(avaliacao);
          return ResponseEntity.ok("Sua avaliação foi salva com sucesso!");
      } else {
          return ResponseEntity.notFound().build();
      }
}

  @PutMapping("/filmes/{id}")
  Optional<Filme> updateFilme(@RequestBody Filme filmeBody, @PathVariable UUID id) {
    Optional<Filme> opt = repository.findById(id);

    if (opt.isPresent()) {
      Filme filme = opt.get();
      opt.get().setTitulo(filmeBody.getTitulo());
      filme.setAutor(filmeBody.getAutor());
      filme.setPais(filmeBody.getPais());
      filme.setEditora(filmeBody.getEditora());
      repository.save(filme);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return opt;
  }

  @DeleteMapping("/filmes/{id}")
  void deleteFilme(@PathVariable UUID id) {
    Optional<Filme> opt = repository.findById(id);
    if(opt.isPresent()){ 
      repository.deleteById(id);
    }else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
   
  }

}
