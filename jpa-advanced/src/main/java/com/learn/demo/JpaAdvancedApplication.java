package com.learn.demo;

import com.learn.demo.entity.FullTimeEmployee;
import com.learn.demo.entity.PartTimeEmployee;
import com.learn.demo.repository.CourseRepository;
import com.learn.demo.repository.EmployeeRepository;
import com.learn.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JpaAdvancedApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAdvancedApplication.class, args);
    }

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
//        List<Review> reviews = List.of(
//                new Review("5", "Great Course - inserted 1"),
//                new Review("4", "Good Course - inserted 2")
//        );
//        courseRepository.addReviewsForCourse(10003L, reviews);
//        Student student = new Student("Jack");
//        Course course = new Course("Microservices in 100 Steps");
//
//        studentRepository.insertStudentAndCourse(student, course);

        employeeRepository.save(new PartTimeEmployee("Jack", new BigDecimal(50)));
        employeeRepository.save(new FullTimeEmployee("Jill", new BigDecimal(10000)));

        log.info("All Part Time Employees {}", employeeRepository.findAllPartTimeEmployees());
        log.info("All Full Time Employees {}", employeeRepository.findAllFullTimeEmployees());
    }
}
