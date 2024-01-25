package it.epicode.DAO;

import it.epicode.Entities.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PersonaDAO {
    private final EntityManager em;

    public PersonaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Persona l) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(l);
        transaction.commit();
        System.out.println("La Persona" + l + "salvata nel DB in modo corretto");
    }

    public Persona getById(long id) {
        return em.find(Persona.class, id);
    }

    public void delete(long id) {
        Persona eventoById = em.find(Persona.class, id);
        if (eventoById != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(eventoById);
            transaction.commit();
        } else {
            System.err.println("Persona con id" + id + "non esistente");
        }
    }
}
