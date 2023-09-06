package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Student;
import org.example.persistence.MyPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        // 1. Create EntityManagerFactory based on the persistence.xml file
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        // 2 Create EntityManagerFactory using the persistence unit info
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                new MyPersistenceUnitInfo(),
                new HashMap<>()
        );

        EntityManager entityManager = emf.createEntityManager(); // through the entity manager we can operate on the context

        try{
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class,1);
            System.out.println(student);
            entityManager.getTransaction().commit();

            // Operations
            // entityManager.find() -> finds by pk(id) and add the the entity to the context form db
            // entityManager.persist() -> add entity to the context
            // entityManager.remove() -> mark entity for removal
            // entityManager.merge() -> merge an entity outside the context to the context it uses the pk to do that
            // entityManager.refresh() -> mirror the context from db
            // entityManager.detach() -> remove entity from context so any change to it will not be reflected in db
        }catch (Exception ex)
        {

        }finally {
            entityManager.close();
        }
    }
}