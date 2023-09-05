package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Student;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager entityManager = emf.createEntityManager(); // through the entity manager we can operate on the context

        try{
            entityManager.getTransaction().begin();
            Student student = new Student();
            student.setId(1L);
            student.setName("hello");

            entityManager.persist(student);
            entityManager.getTransaction().commit();
        }catch (Exception ex)
        {

        }finally {
            entityManager.close();
        }
    }
}