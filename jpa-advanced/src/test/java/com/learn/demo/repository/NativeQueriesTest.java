package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class NativeQueriesTest {

    @Autowired
    EntityManager em;

    @Test
    void selectAllFromCourse_basic() {
        Query query = em.createNativeQuery("SELECT * FROM course", Course.class);
        List resultList = query.getResultList();
        log.info("Select c From Course c -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_with_parameters() {
        Query query = em.createNativeQuery("SELECT * FROM course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        log.info("Select c From Course c where id = 10001 -> {}", resultList);
    }

    @Test
    void selectAllFromCourse_with_parameters_named() {
        Query query = em.createNativeQuery("SELECT * FROM course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        log.info("Select c From Course c where id = 10001 -> {}", resultList);
    }

    @Test
    @Transactional
    void native_queries_to_update() {
        Query query = em.createNativeQuery("update course set last_updated_date=CURRENT_TIMESTAMP()");
        int noOfRowsUpdated = query.executeUpdate();
        log.info("No of rows updated -> {}", noOfRowsUpdated);
    }

}
