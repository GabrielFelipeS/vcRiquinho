package br.com.ifsp.vcRiquinho.base.db.implementation;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryPostgres {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("vcriquinho-postgres");
        }

        return emf;
    }
}
