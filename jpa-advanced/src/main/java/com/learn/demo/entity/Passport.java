package com.learn.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @OneToOne(mappedBy = "passport")
    private Student student;

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }


    public Passport(String number) {
        this.number = number;
    }
}
