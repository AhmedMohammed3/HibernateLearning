package com.learn.demo.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public Review(String rating, String description) {
        this.rating = rating;
        this.description = description;
    }
}
