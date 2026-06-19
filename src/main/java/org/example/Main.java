package org.example;

import entities.*;
import entities.DAO.CatalogoDAO;
import entities.DAO.PrestitoDAO;
import entities.DAO.UtenteDAO;
import entities.exceptions.ElementoNonTrovatoException;
import entities.exceptions.SalvataggioException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TENTATIVO DI CONNESSIONE AL DATABASE IN CORSO ===");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            // inizializzazione dell'EntityManager
            emf = Persistence.createEntityManagerFactory("catalogo-libri");
            em = emf.createEntityManager();

            System.out.println("=================================================");
            System.out.println("CONNESSIONE RIUSCITA CON SUCCESSO!");
            System.out.println("Il database risponde  ed è configurato con successo.");
            System.out.println("=================================================");

            // istanzio DAO passandogli l'EntityManager attivo
            CatalogoDAO catalogoDAO = new CatalogoDAO(em);
            UtenteDAO utenteDAO = new UtenteDAO(em);
            PrestitoDAO prestitoDAO = new PrestitoDAO(em);


            System.out.println("--- INIZIO CREAZIONE E SALVATAGGIO DATI ---");

            // creo libri
            Libri libro1 = new Libri("978-8830455123", "L'ultimo segreto di Parigi", 2018, 412, "Matteo Bianchi", "Thriller");
            Libri libro2 = new Libri("978-8817099431", "Sotto le stelle di Napoli", 2021, 320, "Elena Russo", "Rosa");
            Libri libro3 = new Libri("978-8804712345", "Il custode del tempo", 2015, 580, "Fabio Volpi", "Fantasy");
            Libri libro4 = new Libri("978-8865439876", "Algoritmi e batticuore", 2024, 295, "Andrea De Luca", "Saggistica");
            Libri libro5 = new Libri("978-8809887766", "La cucina della nonna moderna", 2023, 210, "Chiara Giordano", "Culinaria");

            // creo le riviste
            Riviste rivista1 = new Riviste("ISSN-1122-3344", "Tech Tomorrow", 2026, 64, PeriodicitaRiviste.MENSILE);
            Riviste rivista2 = new Riviste("ISSN-5566-7788", "Le Ricette del Borgo", 2025, 80, PeriodicitaRiviste.SETTIMANALE);
            Riviste rivista3 = new Riviste("ISSN-9900-1122", "Focus Spazio", 2026, 96, PeriodicitaRiviste.MENSILE);
            Riviste rivista4 = new Riviste("ISSN-3344-5566", "Arredamento Oggi", 2025, 120, PeriodicitaRiviste.MENSILE);
            Riviste rivista5 = new Riviste("ISSN-7788-9900", "Viaggi e Culture", 2026, 145, PeriodicitaRiviste.SEMESTRALE);

            // creo 6 utenti
            Utente utente1 = new Utente("Marco", "Rossi", LocalDate.of(1990, 5, 12), "TESS-001");
            Utente utente2 = new Utente("Giulia", "Verdi", LocalDate.of(1995, 8, 24), "TESS-002");
            Utente utente3 = new Utente("Antonio", "Esposito", LocalDate.of(1988, 11, 2), "TESS-003");
            Utente utente4 = new Utente("Sofia", "Ferrari", LocalDate.of(2000, 3, 15), "TESS-004");
            Utente utente5 = new Utente("Luigi", "Romano", LocalDate.of(1975, 7, 30), "TESS-005");
            Utente utente6 = new Utente("Beatrice", "Gallo", LocalDate.of(1993, 1, 18), "TESS-006");

            // salvo utenti nel db
            System.out.println("Esecuzione salvataggio Utenti...");
            utenteDAO.salva(utente1);
            utenteDAO.salva(utente2);
            utenteDAO.salva(utente3);
            utenteDAO.salva(utente4);
            utenteDAO.salva(utente5);
            utenteDAO.salva(utente6);


            //   metodo salva
            System.out.println("Esecuzione salvataggio Libri...");
            catalogoDAO.salva(libro1);
            catalogoDAO.salva(libro2);
            catalogoDAO.salva(libro3);
            catalogoDAO.salva(libro4);
            catalogoDAO.salva(libro5);

            System.out.println("Esecuzione salvataggio Riviste...");
            catalogoDAO.salva(rivista1);
            catalogoDAO.salva(rivista2);
            catalogoDAO.salva(rivista3);
            catalogoDAO.salva(rivista4);
            catalogoDAO.salva(rivista5);


            // per la logica dei prestiti andrò a recuperare dal db tramite l'em e tramite l'id PK
            // dei libri o riviste per simulare dei prestiti.
            // visto che sarà una lista di prestiti la istanzio come un array vuoto

            //libri.class => visto che il db non sa cosa sia una classe perchè vede solo tabelle e colonne,
            // con find libri.class sto dicendo all'em di cercare e trovare dalla tabella libri che corrisponde appunto
            // alla class Libri di cercare il libro con la pk che gli ho fornito.

            // --- RICERCA LIBRI ---
            Libri l1 = em.createQuery("SELECT l FROM Libri l WHERE l.codice_isbn = :isbn", Libri.class)
                    .setParameter("isbn", "978-8830455123")
                    .getSingleResult();

            Libri l2 = em.createQuery("SELECT l FROM Libri l WHERE l.codice_isbn = :isbn", Libri.class)
                    .setParameter("isbn", "978-8817099431")
                    .getSingleResult();

            Libri l3 = em.createQuery("SELECT l FROM Libri l WHERE l.codice_isbn = :isbn", Libri.class)
                    .setParameter("isbn", "978-8804712345")
                    .getSingleResult();


            Riviste r1 = em.createQuery("SELECT r FROM Riviste r WHERE r.codice_isbn = :isbn", Riviste.class)
                    .setParameter("isbn", "ISSN-1122-3344")
                    .getSingleResult();

            Riviste r2 = em.createQuery("SELECT r FROM Riviste r WHERE r.codice_isbn = :isbn", Riviste.class)
                    .setParameter("isbn", "ISSN-5566-7788")
                    .getSingleResult();


            Prestito[] prestiti = {
                    new Prestito(utente1, l1, LocalDate.now()),
                    new Prestito(utente2, l2, LocalDate.now()),
                    new Prestito(utente3, l3, LocalDate.now()),
                    new Prestito(utente6, r1, LocalDate.now())
            };

// salvo con for
            System.out.println("\n--- INIZIO CICLO DI SALVATAGGIO CON FOREACH ---");

            for (Prestito p : prestiti) {

                //leggo l'utente, se utente non è null e l'elemento prestato esiste allora salvo, altrimenti errore
                if (p.getUtente() != null && p.getElementoPrestato() != null) {
                    System.out.println("Salvataggio prestito per utente: " + p.getUtente().getNome());
                    prestitoDAO.salva(p);
                }  else {
                    // lancio la mia custom exception
                    throw new SalvataggioException("Errore: Impossibile creare il prestito (utente o elemento mancante).");
                }
            }


            //testo il metodo di rimozione di un elemento tramite codice isbn che ho collocato in catalogoDAO

            try {

                catalogoDAO.rimuoviElementoPerIsbn("978-8830455123");

                System.out.println("Operazione completata con successo.");

            } catch (ElementoNonTrovatoException e) {
                System.err.println("Errore: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Errore imprevisto: " + e.getMessage());
                e.printStackTrace();
            }



















            System.out.println("--- OPERAZIONI COMPLETATE ---");

        } catch (Exception e) {
            System.out.println("=================================================");
            System.out.println(" ERRORE DURANTE L'ESECUZIONE!");
            e.printStackTrace(); //ho inserito lo stacktrace perchè ho visto che è un metodo che mi stampa
            //la causa dell'errore, l'ho aggiunto perchè in terminale avevo un errore di salvataggio dato dal
            //fatto che eseguendo il codice mi continuava a salvare gli utenti con la stessa tessera e considerato che la tessera ha valore Unique continuava a lanciarmi in errore

            System.out.println("Dettagli dell'errore: " + e.getMessage());
            System.out.println("=================================================");
        } finally {
            // chiudo l'em e il database
            System.out.println("=== CHIUSURA IN CORSO  ===");
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
            System.out.println("Operazioni terminate, processo concluso");
        }
    }
}