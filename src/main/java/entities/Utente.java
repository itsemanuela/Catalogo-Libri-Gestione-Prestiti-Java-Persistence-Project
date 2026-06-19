package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    // Vincolo UNIQUE: due utenti non possono  avere lo stesso numero di tessera, l'ho inserito anche nel diagramma
    @Column(name = "numero_tessera", unique = true, nullable = false)
    private String numeroTessera;


    public Utente() {}

    // costruttore per registrare nuovi utenti nel Main
    public Utente(String nome, String cognome, LocalDate dataNascita, String numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }

    // Getter e Setter
    public UUID getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

    public String getNumeroTessera() { return numeroTessera; }
    public void setNumeroTessera(String numeroTessera) { this.numeroTessera = numeroTessera; }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utente{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", cognome='").append(cognome).append('\'');
        sb.append(", dataNascita=").append(dataNascita);
        sb.append(", numeroTessera='").append(numeroTessera).append('\'');
        sb.append('}');
        return sb.toString();
    }
}