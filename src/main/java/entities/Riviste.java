package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "riviste")
public class Riviste extends Catalogo {

    @Enumerated(EnumType.STRING) // per l'enum
    @Column(name = "periodicita", nullable = false)
    private PeriodicitaRiviste periodicitaRiviste;

    // Costruttore vuoto
    public Riviste() {
    }


    public Riviste(String codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, PeriodicitaRiviste periodicitaRiviste) {
        super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicitaRiviste = periodicitaRiviste;
    }


    public PeriodicitaRiviste getPeriodicitaRiviste() {
        return periodicitaRiviste;
    }

    public void setPeriodicitaRiviste(PeriodicitaRiviste periodicitaRiviste) {
        this.periodicitaRiviste = periodicitaRiviste;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Riviste{");
        sb.append("periodicitaRiviste=").append(periodicitaRiviste);
        sb.append('}');
        return sb.toString();
    }
}