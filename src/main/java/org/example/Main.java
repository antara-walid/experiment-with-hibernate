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
            Student student = new Student();
            student.setId(2L);
            student.setName("world");

            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {

        }finally {
            entityManager.close();
        }
    }
}