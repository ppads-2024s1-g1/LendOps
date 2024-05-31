package br.mackenzie.restapi.medias.entity;
// import java.util.UUID;
// import javax.persistence.GeneratedValue;
//import java.util.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;
// import javax.persistence.*;
// import org.hibernate.annotations.GenericGenerator;
// import org.hibernate.annotations.Parameter;
// import br.mackenzie.restapi.Avaliacao.Avaliacao;
import javax.persistence.*;

//import br.mackenzie.restapi.Avaliacao.Avaliacao;

import java.util.*;



@Entity
@Table(name = "medias")
@Inheritance(strategy = InheritanceType.JOINED)
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private int ano;
  private String titulo;
  private String autor;
  private String pais;
  private String editora;
  private String descricao;


  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }


  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public String getPais() {
    return pais;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getEditora() {
    return editora;
  }

  public void setEditora(String editora) {
    this.editora = editora;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }


}
