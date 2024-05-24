package br.mackenzie.restapi.Avaliacao;
//import java.util.*;
import javax.persistence.*;
// import org.hibernate.annotations.GenericGenerator;
// import org.hibernate.annotations.Parameter;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;
import java.time.*;
import java.util.UUID;


@Entity
@Table(name="avaliacoes")
public class Avaliacao {
    @Id 
    @GeneratedValue
    private UUID id;
    private String autor;   
    private String comment;
    private UUID itemId;

    
    public UUID getItemId() {
        return itemId;
    }
    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public LocalDate getDate() {
        return LocalDate.now();
    }
    public LocalTime getTime() {
        return LocalTime.now();
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

}
