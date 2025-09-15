package com.learn.demo;

import com.learn.demo.entity.Course;
import com.learn.demo.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JpaAdvancedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedApplication.class, args);
    }

    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
//        courseRepository.playWithEntityManager();
//        Course course = courseRepository.findById(10001L);
//        log.info("Course with id 10001: {}", course);
//        log.info("==============================================");
//        courseRepository.deleteById(10001L);
//        log.info("Course with id 10001: {}", courseRepository.findById(10001L));
//        log.info("==============================================");
    }
}
