package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Passport;
import com.learn.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        System.out.println("================================================");
        log.info("Student {}", student);
        log.info("Student Passport {}", student.getPassport());
        System.out.println("================================================");
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        System.out.println("================================================");
        System.out.println("Passport " + passport);
        System.out.println("Passport Student " + passport.getStudent());
        System.out.println("================================================");
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        System.out.println("================================================");
        Student student = em.find(Student.class, 20001L);
        log.info("Student {}", student);
        log.info("Student Courses {}", student.getCourses());
        System.out.println("================================================");
    }
}
