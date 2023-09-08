package org.example;

import com.mysql.cj.xdevapi.AddResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Address;
import org.example.entities.Student;
import org.example.persistence.MyPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // 1. Create EntityManagerFactory based on the persistence.xml file
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

        // 2 Create EntityManagerFactory using the persistence unit info
        Map<String,String> settings = new HashMap<>();
        settings.put("hibernate.show_sql","true");
        settings.put("hibernate.hbm2ddl.auto","create");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
                new MyPersistenceUnitInfo(),
                settings
        );

        EntityManager entityManager = emf.createEntityManager(); // through the entity manager we can operate on the context

        try{
            entityManager.getTransaction().begin();
            Student student = new Student();
            student.setName("test");
            Address address = new Address();
            address.setStreet("123");
            address.setStudent(student);
            student.setAddresses(List.of(address));

//            entityManager.persist(address);  // because we use cascade persist
            entityManager.persist(student);
            entityManager.persist(address);
            entityManager.getTransaction().commit();

            // Operations
            // entityManager.find() -> finds by pk(id) and add the the entity to the context form db
            // entityManager.persist() -> add entity to the context
            // entityManager.remove() -> mark entity for removal
            // entityManager.merge() -> merge an entity outside the context to the context it uses the pk to do that
            // entityManager.refresh() -> mirror the context from db
            // entityManager.detach() -> remove entity from context so any change to it will not be reflected in db
            //entityManager.getReference() -> like a lazy loading version of find, hibernate will not select from db until it is necessary to improve performances
        }catch (Exception ex)
        {

        }finally {
            entityManager.close();
        }
    }
}