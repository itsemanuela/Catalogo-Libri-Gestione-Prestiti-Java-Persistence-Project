package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "libri")
public class Libri extends  Catalogo {

    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;

    //  Costruttore vuoto
    public Libri() {}


    // costruttore pieno che userò  nel Main per creare nuovi libri
    public Libri(String codice_isbn, String titolo, int anno_pubblicazione, int numero_pagine, String autore, String genere) {
        // super() richiama il costruttore della classe madre Catalogo
        super(codice_isbn, titolo, anno_pubblicazione, numero_pagine);
        this.autore = autore;
        this.genere = genere;
    }

    // Getter e Setter
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }

    public String getGenere() { return genere; }
    public void setGenere(String genere) { this.genere = genere; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Libri{");
        sb.append("autore='").append(autore).append('\'');
        sb.append(", genere='").append(genere).append('\'');
        sb.append('}');
        return sb.toString();
    }
}