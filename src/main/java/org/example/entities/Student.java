package org.example.entities;

import jakarta.persistence.*;
import org.example.generators.UUIDGenerator;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
public class Student extends Person{
    private String cne;

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
}
