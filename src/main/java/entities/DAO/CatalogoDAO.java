package entities.DAO;

import entities.Catalogo;
import entities.exceptions.SalvataggioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CatalogoDAO {

    private final EntityManager em;

    // Il costruttore riceve l'EntityManager dal Main
    public CatalogoDAO(EntityManager em) {
        this.em = em;
    }

    // METODO PER SALVARE UN ELEMENTO (Accetta sia Libro che Rivista grazie a Catalogo)
    public void salva(Catalogo elemento) {

        //Prima di fare qualsiasi operazione di scrittura (inserimento, modifica o cancellazione),
        // chiedo all' EntityManager occuparsi del controllo della transazione.
        EntityTransaction transazione = em.getTransaction();

        try {
            //qui racchiudo il un blocco di controllo l'inizio della transazione: da questo momento in poi,
            // il DB lavora per controllare se le cose andranno a buon fine.
            transazione.begin();

            //a questo punto del PERSIST i dati non vengono ancora inseriti nel database, ma entrano in uno
            // stato di MANAGED, ossia gestito dalla persistenza di HIB.

            em.persist(elemento);

            // questa è la parte definitiva, quando faccio partire il commit della transacion, "l'ordine" in stato di attesta viene spedito al database. Nel mio caso, avendo scelto la strategy della joined table, verranno inserire "dietro le quinte" due query di insert distinte, una per la tabella madre
            // e due per le tabelle figlie con i propri attributi.

            transazione.commit();
            System.out.println("Elemento salvato con successo! Titolo: " + elemento.getTitolo());
        } catch (SalvataggioException e) {

            System.err.println("Errore durante il salvataggio dell'elemento: " + e.getMessage());
        }
    }
}