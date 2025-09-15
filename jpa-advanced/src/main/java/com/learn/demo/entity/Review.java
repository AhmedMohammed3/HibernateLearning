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

public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String rating;

    private String description;


    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
