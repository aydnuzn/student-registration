package com.works.schoolregistration.repository;

import com.works.schoolregistration.enumeration.CourseName;
import com.works.schoolregistration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Transactional
    @Query(value = "Delete From students_courses WHERE course_id = ?1", nativeQuery = true)
    void deleteCourses(Long courseId);

    Optional<Course> findByNameEquals(CourseName name);

    List<Course> findByOrderByIdAsc();

}
