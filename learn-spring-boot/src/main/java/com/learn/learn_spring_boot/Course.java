package com.learn.learn_spring_boot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Course {
    private long id;
    private String name;
    private String author;
}
