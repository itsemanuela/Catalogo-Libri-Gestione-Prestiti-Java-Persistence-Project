package entities.DAO;

import entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PrestitoDAO {

    private final EntityManager em;

    //  costruttore riceve l'EntityManager attivo dal Main
    public PrestitoDAO(EntityManager em) {
        this.em = em;
    }

    // METODO PER SALVARE UN PRESTITO
    public void salva(Prestito prestito) {
        EntityTransaction transazione = em.getTransaction();

        try {
            // inizio la transazione per la scrittura del prestito
            transazione.begin();

            // L'oggetto entra nello stato di MANAGED
            em.persist(prestito);

            // insert nel database sulla tabella prestiti
            transazione.commit();
            System.out.println("Prestito registrato con successo nel database!");

        } catch (Exception e) {
            System.err.println("--- ERRORE SALVATAGGIO PRESTITO ---");
            System.err.println("Impossibile registrare il prestito corrente.");
            System.err.println("Dettaglio tecnico dell'errore: " + e.getMessage());
        }
    }



}