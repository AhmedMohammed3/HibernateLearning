package com.learn.demo.repository;

import com.learn.demo.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path="courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    Integer countByName(String name);

    @Query("select c from Course c where c.name = ?1 and c.students is not empty")
    Optional<Course> findByNameAndStudentsNotEmpty(@NonNull String name);

    List<Course> findByNameLikeOrderByNameAsc(String name, Pageable pageable);
}
