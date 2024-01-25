package it.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static it.epicode.Utils.Jpa.getEntityManagerFactory;

public class Application {

    private static final EntityManagerFactory emf = getEntityManagerFactory();


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();


            em.close();
            emf.close();
        }

    }

