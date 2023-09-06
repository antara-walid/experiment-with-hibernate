package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.example.generators.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Student {
    @Id
    @GenericGenerator(name="UUIDGenerator",type= UUIDGenerator.class)
    @GeneratedValue(generator = "UUIDGenerator")
    private String id;

    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
