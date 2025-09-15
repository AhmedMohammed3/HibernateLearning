package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class JPQLTest {

    @Autowired
    EntityManager em;

    @Test
    void selectAllFromCourse_basic() {
        Query query = em.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_typed() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_where() {
        TypedQuery<Course> typedQuery = em.createNamedQuery("query_get_all_courses_by_name_like", Course.class);
        typedQuery.setParameter("name", "%100 steps%");
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c where name like '%100 steps%' -> {}", resultList);
    }

}
