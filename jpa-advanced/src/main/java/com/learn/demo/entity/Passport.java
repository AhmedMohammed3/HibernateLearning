package com.learn.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Passport {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;


    public Passport(String number) {
        this.number = number;
    }
}
