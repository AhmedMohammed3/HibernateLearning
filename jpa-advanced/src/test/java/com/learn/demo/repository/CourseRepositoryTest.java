package com.learn.demo.repository;

import com.learn.demo.JpaAdvancedApplication;
import com.learn.demo.entity.Course;
import com.learn.demo.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaAdvancedApplication.class)
@Slf4j
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    @DirtiesContext
    void deleteById_basic() {
        courseRepository.deleteById(10002L);
        assertNull(courseRepository.findById(10002L));
    }

    @Test
    void findById_basic() {
        Course course = courseRepository.findById(10002L);
        assertEquals("Spring in 50 steps", course.getName());
    }

    @Test
    @DirtiesContext
    void save_update_basic() {
        Course course = courseRepository.findById(10002L);
        assertEquals("Spring in 50 steps", course.getName());
        course.setName("Spring in 50 steps - Updated");
        courseRepository.save(course);
        assertEquals("Spring in 50 steps - Updated", courseRepository.findById(10002L).getName());
    }

    @Test
    @DirtiesContext
    void save_insert_basic() {
        Course course = new Course("Spring in 50 steps");
        courseRepository.save(course);
        assertEquals("Spring in 50 steps", courseRepository.findById(course.getId()).getName());
    }

    @Test
    @Transactional
    public void retrieveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        log.info("Course.getReviews() {}", course.getReviews());
    }

    @Test
    @Transactional
    public void retrieveCourseForReview() {
        Review review = em.find(Review.class, 50001L);
        log.info("Review.getCourse() {}", review.getCourse());
    }


    @Test
//    @Transactional
    public void findById_firstLevelCacheDemo() {
        Course course = courseRepository.findById(10001L);
        log.info("First course retrieved {}", course);
        assertEquals("JPA in 50 steps", course.getName());
        
        Course course1 = courseRepository.findById(10001L);
        log.info("First course retrieved again {}", course1);
        assertEquals("JPA in 50 steps", course1.getName());
    }
}
