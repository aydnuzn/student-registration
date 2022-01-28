package com.works.schoolregistration.service;

import com.works.schoolregistration.dto.CourseDto;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface CourseService {

    List<Student> studentsOnCourse(String courseName);

    List<Course> getAllCourses();

    Course getCourse(Long courseId);

    Course createCourse(CourseDto courseDto);

    Course updateCourse(Long courseId, CourseDto courseDto);

    Course deleteCourse(Long courseId);
}
