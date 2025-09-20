package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class CourseSpringDataRepositoryTest {
    @Autowired
    CourseSpringDataRepository repository;

    @Test
    void findById_coursePresent() {
        System.out.println("==================================================================================================================================================");
        Optional<Course> courseOptional = repository.findById(10001L);
        assertTrue(courseOptional.isPresent());
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void findById_courseNotPresent() {
        System.out.println("==================================================================================================================================================");
        Optional<Course> courseOptional = repository.findById(20001L);
        assertFalse(courseOptional.isPresent());
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void playingAroundWithSpringDataJpaRepository() {
        System.out.println("==================================================================================================================================================");
        Course course = new Course("Microservices in 100 Steps");
        repository.save(course);

        course.setName("Microservices in 100 Steps - Updated");
        repository.save(course);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void sort() {
        System.out.println("==================================================================================================================================================");
        Sort sort = Sort.by(Sort.Direction.DESC, "name").and(Sort.by(Sort.Direction.ASC, "id"));
        List<Course> courses = repository.findAll(sort);
        log.info("All courses in desc name and asc id: {}", courses);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void pagination() {
        System.out.println("==================================================================================================================================================");
        PageRequest pageRequest1 = PageRequest.of(0, 3);
        Page<Course> page1 = repository.findAll(pageRequest1);
        log.info("First page - {}", page1.getContent());

        Pageable pageRequest2 = page1.nextPageable();
        Page<Course> page2 = repository.findAll(pageRequest2);
        log.info("Second page - {}", page2.getContent());

        Pageable pageRequest3 = page2.nextPageable();
        Page<Course> page3 = repository.findAll(pageRequest3);
        log.info("Third page - {}", page3.getContent());
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void findByName() {
        System.out.println("==================================================================================================================================================");
        List<Course> courses = repository.findByName("JPA in 50 steps");
        log.info("Courses with name 'JPA in 50 steps': {}", courses);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void countByName() {
        System.out.println("==================================================================================================================================================");
        Integer count = repository.countByName("JPA in 50 steps");
        log.info("Count of courses with name 'JPA in 50 steps': {}", count);
        System.out.println("==================================================================================================================================================");
    }
}
