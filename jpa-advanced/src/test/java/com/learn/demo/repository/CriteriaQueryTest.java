package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class CriteriaQueryTest {

    @Autowired
    EntityManager em;

    @Test
    void selectAllFromCourse_all() {
        // Select c From Course c

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_having_100Steps() {
        // Select c From Course c where name like '%100 Steps%'

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate like100Steps = cb.like(courseRoot.get("name"), "%100 steps%");

        cq.where(like100Steps);

        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_without_student() {
        // Select c From Course c where c.students is empty

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        Predicate studentsIsEmpty = cb.isEmpty(courseRoot.get("students"));

        cq.where(studentsIsEmpty);

        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_join() {
        // Select c, s From Course c join c.students s

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students");


        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_left_join() {
        // Select c, s From Course c join c.students s

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        Root<Course> courseRoot = cq.from(Course.class);

        courseRoot.join("students", JoinType.LEFT);


        TypedQuery<Course> typedQuery = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }
}
