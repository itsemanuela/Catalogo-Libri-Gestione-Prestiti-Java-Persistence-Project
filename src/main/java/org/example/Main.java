package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== TENTATIVO DI CONNESSIONE AL DATABASE IN CORSO ===");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {

            emf = Persistence.createEntityManagerFactory("catalogo-libri");
            em = emf.createEntityManager();

            System.out.println("=================================================");
            System.out.println("CONNESSIONE RIUSCITA CON SUCCESSO!");
            System.out.println("Il database risponde e JPA/Jakarta è configurato bene.");
            System.out.println("=================================================");

        } catch (Exception e) {
            System.out.println("\n=================================================");
            System.out.println(" ERRORE DI CONNESSIONE AL DATABASE!");
            System.out.println("Dettagli dell'errore:");
            System.out.println("=================================================");
        }
    }
}