package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import com.learn.demo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    void selectAllFromCourse_courses_without_students() {
        System.out.println("==================================================================================================================================================");
        TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where c.students is empty", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c where c.students is empty -> {}", resultList);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void selectAllFromCourses_courses_with_at_least_two_student() {
        System.out.println("==================================================================================================================================================");
        TypedQuery<Course> typedQuery = em.createQuery("select c from Course c where size(c.students) >= 2", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        log.info("Select c From Course c where size(c.students) >= 2 -> {}", resultList);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void selectAllFromCourses_courses_ordered_by_students() {
        System.out.println("==================================================================================================================================================");
        TypedQuery<Course> typedQuery = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = typedQuery.getResultList();
        log.info("select c from Course c order by size(c.students) desc -> {}", resultList);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    void selectAllFromStudents_with_passports_in_a_certain_pattern() {
        System.out.println("==================================================================================================================================================");
        TypedQuery<Student> typedQuery = em.createQuery("select s from Student s where s.passport.number like '%1234%'", Student.class);
        List<Student> resultList = typedQuery.getResultList();
        log.info("select s from Student s where s.passport.number like '%1234%' -> {}", resultList);
        System.out.println("==================================================================================================================================================");
    }

    @Test
    public void join() {
        System.out.println("==================================================================================================================================================");
        String qryStr = "select c, s from Course c join c.students s";
        Query query = em.createQuery(qryStr);
        List<Object[]> resultList = query.getResultList();
        log.info("{} -> ", qryStr);
        resultList.forEach(el -> {
            log.info("Course {}, Student {}", el[0], el[1]);
        });
        System.out.println("==================================================================================================================================================");
    }

    @Test
    public void left_join() {
        System.out.println("==================================================================================================================================================");
        String qryStr = "select c, s from Course c left join c.students s";
        Query query = em.createQuery(qryStr);
        List<Object[]> resultList = query.getResultList();
        log.info("{} -> ", qryStr);
        resultList.forEach(el -> {
            log.info("Course {}, Student {}", el[0], el[1]);
        });
        System.out.println("==================================================================================================================================================");
    }

    @Test
    public void cross_join() {
        System.out.println("==================================================================================================================================================");
        String qryStr = "select c, s from Course c, Student s";
        Query query = em.createQuery(qryStr);
        List<Object[]> resultList = query.getResultList();
        log.info("{} -> ", qryStr);
        resultList.forEach(el -> {
            log.info("Course {}, Student {}", el[0], el[1]);
        });
        System.out.println("==================================================================================================================================================");
    }


}
