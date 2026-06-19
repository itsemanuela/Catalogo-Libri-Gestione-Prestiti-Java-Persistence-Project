package org.example;

import entities.DAO.CatalogoDAO;
import entities.Libri;
import entities.Riviste;
import entities.PeriodicitaRiviste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

            System.out.println("--- INIZIO CREAZIONE E SALVATAGGIO DATI ---");

            // 3. Creiamo un'istanza di un Libro
            Libri nuovoLibro = new Libri("978-8804668237", "Il Nome della Rosa", 1980, 503, "Umberto Eco", "Romanzo Storico");

            // creo una rivista di prova

            Riviste nuovaRivista = new Riviste("ISSN-2041-1723", "Nature Communications", 2026, 85, PeriodicitaRiviste.SETTIMANALE);

            //   metodo salva
            System.out.println("\nEsecuzione salvataggio Libro...");
            catalogoDAO.salva(nuovoLibro);

            System.out.println("Esecuzione salvataggio Rivista...");
            catalogoDAO.salva(nuovaRivista);

            System.out.println("--- OPERAZIONI COMPLETATE ---");

        } catch (Exception e) {
            System.out.println("=================================================");
            System.out.println(" ERRORE DURANTE L'ESECUZIONE!");
            System.out.println("Dettagli dell'errore: " + e.getMessage());
            System.out.println("=================================================");
        }
    }
}