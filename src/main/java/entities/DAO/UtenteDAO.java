package entities.DAO;

import entities.Utente;
import entities.exceptions.SalvataggioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    // METODO PER SALVARE UN UTENTE
    public void salva(Utente utente) {
        EntityTransaction transazione = em.getTransaction();

        try {
            // inizio la transazione per la scrittura dell'utente
            transazione.begin();

            // L'oggetto entra nello stato di MANAGED
            em.persist(utente);

            // insert
            transazione.commit();
            System.out.println("Utente salvato con successo! " + utente.getNome() + " " + utente.getCognome());

        } catch (SalvataggioException e) {


            System.err.println("--- ERRORE SALVATAGGIO UTENTE ---");
            System.err.println("Impossibile salvare l'utente: " + utente.getNome() + " " + utente.getCognome());
            System.err.println("Dettaglio tecnico: " + e.getMessage());

        }
    }
}