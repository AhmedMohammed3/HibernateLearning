package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
public class PerformanceTuningTest {

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("students");

        List<Course> courses = em.createNamedQuery("query_get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();

        for (Course course : courses) {
            log.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    void solvingNPlusOneProblem_EntityGraph_JoinFetch() {
        List<Course> courses = em.createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();

        for (Course course : courses) {
            log.info("Course -> {}, Students -> {}", course, course.getStudents());
        }
    }
}
