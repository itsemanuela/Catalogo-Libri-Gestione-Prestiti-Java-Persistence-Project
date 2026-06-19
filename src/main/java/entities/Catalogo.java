package entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="Catalogo")
@Inheritance(strategy= InheritanceType.JOINED)
//La scelta della strategia Joined sarà ampiamente spiegata nel Readme
public abstract class Catalogo {
    @Id
    @GeneratedValue //voglio gli uuid quindi lascerò il Generated in auto
    @Column(name="idCatalogo", nullable=false, unique=true)
    private UUID idCatalogo;

    @Column(nullable=false, unique=true)
    private String codice_isbn;

    @Column(nullable=false )
     private String titolo;

    @Column(nullable=false)
private int anno_pubblicazione;
    @Column(nullable=false)
private int numero_pagine;

//istanzio l'oggetto in memoria con il costruttore vuoto che serve a Hibernate come "stampo inziale" che gli servirà per la fabbricazione dell'oggetto quando leggerà i dati dal database.

   public Catalogo() {}

    //creo costruttore della classe e gli passo i parametri necessari

    public Catalogo(String codice_isbn, String titolo, int anno_pubblicazione, int numero_pagine) {
       this.codice_isbn = codice_isbn;
       this.titolo = titolo;
       this.anno_pubblicazione = anno_pubblicazione;
       this.numero_pagine = numero_pagine;


    }

    //creo i metodi getter e setter
    public UUID getIdCatalogo() {
       return idCatalogo;
    }
    public void setIdCatalogo(UUID idCatalogo) {
       this.idCatalogo = idCatalogo;
    }
    public String getCodice_isbn() {
       return codice_isbn;

    }
    public void setCodice_isbn(String codice_isbn) {
       this.codice_isbn = codice_isbn;
    }
    public String getTitolo() {
       return titolo;
    }
    public void setTitolo(String titolo) {
       this.titolo = titolo;
    }
    public int getAnno_pubblicazione() {
       return anno_pubblicazione;
    }
    public void setAnno_pubblicazione(int anno_pubblicazione) {
       this.anno_pubblicazione = anno_pubblicazione;
    }
    public int getNumero_pagine() {
       return numero_pagine;

    }
public void setNumero_pagine(int numero_pagine) {
       this.numero_pagine = numero_pagine;
}


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Catalogo{");
        sb.append("idCatalogo=").append(idCatalogo);
        sb.append(", codice_isbn='").append(codice_isbn).append('\'');
        sb.append(", titolo='").append(titolo).append('\'');
        sb.append(", anno_pubblicazione=").append(anno_pubblicazione);
        sb.append(", numero_pagine=").append(numero_pagine);
        sb.append('}');
        return sb.toString();
    }
}
