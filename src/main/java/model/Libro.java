package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Libri")
public class Libro {

    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message = "This field can't be empty")
    String autore, titolo, annoPubblicazione;
    @NotNull(message = "This field can't be empty")
    Double prezzo;

    public Libro(){}

    public Libro(Long id, String autore, String titolo, String annoPubblicazione, Double prezzo) {
        this.id = id;
        this.autore = autore;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.prezzo = prezzo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(String annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }
    @Override
    public String toString() {
        return "Book{" +
                "author='" + autore + '\'' +
                ", title='" + titolo + '\'' +
                ", pubblicationYear='" + annoPubblicazione + '\'' +
                ", price=" + prezzo +
                '}';
    }
}
