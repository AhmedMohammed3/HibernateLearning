package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaAdvancedApplication.class)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DirtiesContext
    void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    void findById_basic() {
        Course course = courseRepository.findById(10002L);
        assertEquals("Spring in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    void save_update_basic() {
        Course course = courseRepository.findById(10002L);
        assertEquals("Spring in 50 steps", course.getName());
        course.setName("Spring in 50 steps - Updated");
        courseRepository.save(course);
        assertEquals("Spring in 50 steps - Updated", courseRepository.findById(10002L).getName());
    }

    @Test
    @DirtiesContext
    void save_insert_basic() {
        Course course = new Course("Spring in 50 steps");
        courseRepository.save(course);
        assertEquals("Spring in 50 steps", courseRepository.findById(course.getId()).getName());
    }

}
