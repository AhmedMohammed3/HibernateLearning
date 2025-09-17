package com.learn.demo.repository;

import com.learn.demo.entity.Course;
import com.learn.demo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseRepository {

    private final EntityManager em;

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
    }

    public void playWithEntityManager() {
        Course course1 = new Course("Web Services in 100 steps");
        em.persist(course1);

        Course course2 = findById(10001L);
        course2.setName("JPA in 50 steps - Updated");
    }

    public void addHardcodedReviewsForCourse() {
        System.out.println("==========================================================================================================================================================================================================");
        Course course = findById(10003L);
        log.info("Course.getReviews() {}", course.getReviews());
//
        Review review1 = new Review("5", "Great Course - inserted 1");
        Review review2 = new Review("4", "Good Course - inserted 2");
//
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        em.persist(review1);
        em.persist(review2);
//
//        log.info("Course {}", course);
        System.out.println("==========================================================================================================================================================================================================");
    }

    public void addReviewsForCourse(Long courseId, List<Review> reviews) {
        System.out.println("==========================================================================================================================================================================================================");
        Course course = findById(courseId);
        log.info("Course.getReviews() {}", course.getReviews());

        for (Review review : reviews) {
            course.addReview(review);
            review.setCourse(course);
            em.persist(review);
        }

        System.out.println("==========================================================================================================================================================================================================");
    }
}
