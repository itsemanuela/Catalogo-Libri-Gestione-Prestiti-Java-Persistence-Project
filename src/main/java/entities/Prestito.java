package entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestiti")
public class Prestito {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // relazione con l'utente: più prestiti possono essere associati allo stesso utente (drawsql)
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    // relazione con il catalogo: più prestiti possono riguardare lo stesso elemento
    @ManyToOne
    @JoinColumn(name = "elemento_catalogo_id", nullable = false)
    private Catalogo elementoPrestato; // punta alla classe madre 'Catalogo' in modo da accettare sia Libri che Riviste
    //è la FK nel mio drawsql

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_prevista", nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva; // sarà NULL finché l'utente non riporta il libro


    public Prestito() {}

    // costruttore
    public Prestito(Utente utente, Catalogo elementoPrestato, LocalDate dataInizioPrestito) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.dataInizioPrestito = dataInizioPrestito;
        // mi calcolo la data di restituzione prevista: la data prevista è sempre +30 giorni dalla data di inizio
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
    }

    // get e set
    public UUID getId() { return id; }

    public Utente getUtente() { return utente; }
    public void setUtente(Utente utente) { this.utente = utente; }

    public Catalogo getElementoPrestato() { return elementoPrestato; }
    public void setElementoPrestato(Catalogo elementoPrestato) { this.elementoPrestato = elementoPrestato; }

    public LocalDate getDataInizioPrestito() { return dataInizioPrestito; }
    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzionePrevista = dataInizioPrestito.plusDays(30); // ricalcolo automatcio, cioè dipende dalla data che inserisco
    }

    public LocalDate getDataRestituzionePrevista() { return dataRestituzionePrevista; }

    public LocalDate getDataRestituzioneEffettiva() { return dataRestituzioneEffettiva; }
    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prestito{");
        sb.append("id=").append(id);
        sb.append(", utente=").append(utente);
        sb.append(", elementoPrestato=").append(elementoPrestato);
        sb.append(", dataInizioPrestito=").append(dataInizioPrestito);
        sb.append(", dataRestituzionePrevista=").append(dataRestituzionePrevista);
        sb.append(", dataRestituzioneEffettiva=").append(dataRestituzioneEffettiva);
        sb.append('}');
        return sb.toString();
    }
}