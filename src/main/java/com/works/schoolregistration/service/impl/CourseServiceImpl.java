package com.works.schoolregistration.service.impl;

import com.works.schoolregistration.dto.CourseDto;
import com.works.schoolregistration.enumeration.CourseName;
import com.works.schoolregistration.exception.EntityNotFoundException;
import com.works.schoolregistration.exception.HttpMessageNotReadableException;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.repository.CourseRepository;
import com.works.schoolregistration.service.CourseService;
import com.works.schoolregistration.util.Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> studentsOnCourse(String courseName) {

        if(!Util.checkStringInsideEnum(courseName)){
            throw new HttpMessageNotReadableException("Unprocessable Input Data - No Enum Constant");
        }
        CourseName name = CourseName.valueOf(courseName);
        Course course = courseRepository.findByNameEquals(name)
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        List<Student> studentList = course.getStudents();

        return studentList;
    }

    @Override
    public List<Course> getAllCourses() {

        return courseRepository.findByOrderByIdAsc();
    }

    @Override
    public Course getCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        return course;
    }

    @Override
    public Course createCourse(CourseDto courseDto) {

        Course course = Course.builder()
                .name(courseDto.getName())
                .students(null)
                .build();
        course = courseRepository.save(course);

        return course;
    }

    @Override
    public Course updateCourse(Long courseId, CourseDto courseDto) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        course.setName(courseDto.getName());
        course = courseRepository.saveAndFlush(course);

        return course;
    }

    @Transactional
    @Override
    public Course deleteCourse(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));
        courseRepository.deleteCourses(course.getId());
        courseRepository.delete(course);

        return course;
    }

}
