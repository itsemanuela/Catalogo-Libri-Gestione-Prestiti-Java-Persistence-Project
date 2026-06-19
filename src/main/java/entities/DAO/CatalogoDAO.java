package entities.DAO;

import entities.Catalogo;
import entities.exceptions.ElementoNonTrovatoException;
import entities.exceptions.SalvataggioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.util.List;

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

    // rimozione dal catalogo tramine codice ISBN

    public void rimuoviElementoPerIsbn(String isbn) throws ElementoNonTrovatoException {
        EntityTransaction transazione = em.getTransaction();
        try {
            transazione.begin();

            // trovo l'elemento || questo è anche metodo richiesto cerca per isbn
            Catalogo elemento = em.createQuery("SELECT c FROM Catalogo c WHERE c.codice_isbn = :isbn", Catalogo.class)
                    .setParameter("isbn", isbn)
                    .getSingleResult();

            // elimino prima i prestiti all isbn collegato per un errore persistente nel terminale
            // se un isbn è collegato ad un prestito non posso rimuoverlo

            em.createQuery("DELETE FROM Prestito p WHERE p.elementoPrestato = :elemento")
                    .setParameter("elemento", elemento)
                    .executeUpdate();

            // elimino
            em.remove(elemento);

            transazione.commit();
            System.out.println("Elemento con ISBN " + isbn + " rimosso con successo.");

        } catch (NoResultException e) {

            throw new ElementoNonTrovatoException("Non ho trovato nessun libro/rivista con ISBN: " + isbn);
        }
    }

//  metodo cerca per titolo O PARTE DI ESSO
    // mi deve tornare una lista perchè probabile che io abbia piu di un risultato uguale
public List<Catalogo> cercaPerTitolo(String titolo) {
        //mi creo la query e selzioni dal catologo i titoli
    return em.createQuery("SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo", Catalogo.class)
            // setto i parametri con % che è un carattere speciale che nella ricerca LIKE
            // indica al database di trovare qualsiasi sequenza di caratteri prima, dopo o in mezzo al testo cercato
            .setParameter("titolo", "%" + titolo + "%")
            .getResultList();
}





}