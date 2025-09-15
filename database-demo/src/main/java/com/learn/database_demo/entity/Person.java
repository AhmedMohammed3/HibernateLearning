package com.learn.database_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
@NamedQuery(name = "findAll", query = "SELECT p FROM Person p")
public class Person {
    @Id
    @GeneratedValue
    private int id;

    public Person(String name, String location, Date birthDate) {
        this.name = name;
        this.location = location;
        this.birthDate = birthDate;
    }

    private String name;
    private String location;
    private Date birthDate;
}
