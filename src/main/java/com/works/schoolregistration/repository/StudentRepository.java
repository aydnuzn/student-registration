package com.works.schoolregistration.repository;

import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.projection.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByNumberEquals(String number);

    @Query(value = "SELECT id, `name`, surname, gender, number FROM student LEFT JOIN students_courses \n" +
            "ON student.id = students_courses.student_id WHERE student_id IS NULL;", nativeQuery = true)
    List<StudentInfo> AllStudentsNotAttendCourse();

}
