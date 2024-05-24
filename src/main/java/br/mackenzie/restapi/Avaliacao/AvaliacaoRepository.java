package br.mackenzie.restapi.Avaliacao;

import java.util.*;
import java.util.UUID;

import org.hibernate.mapping.List;

// import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;




public interface AvaliacaoRepository extends JpaRepository<Avaliacao, UUID> {
    
    java.util.List<Avaliacao> findByItemId(UUID itemId);
    
}
