package org.example.entities;

import jakarta.persistence.*;
import org.example.generators.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @OneToMany
    @JoinColumn(name = "student_id") // in the case of oneToMany relationship the joinColumn must be used to avoid the creation of a join Table
    private List<Address> addresses ;


    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
