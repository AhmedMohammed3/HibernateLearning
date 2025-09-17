package com.learn.demo.repository;

import com.learn.demo.entity.Course;
import com.learn.demo.entity.Passport;
import com.learn.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Transactional
public class StudentRepository {

    private final EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        em.remove(student);
    }

    public void save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
    }

    public void saveStudentWihPassport() {
        Passport passport = new Passport("Z123456");
        em.persist(passport);
        Student student = new Student("Mike");
        student.setPassport(passport);
        em.persist(student);
    }

    public void insertHardcodedStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("Microservices in 100 Steps");

        em.persist(course);
        em.persist(student);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course) {

        em.persist(course);
        em.persist(student);

        student.addCourse(course);
        course.addStudent(student);

        em.persist(student);
    }
}
