package com.works.schoolregistration.service.impl;

import com.works.schoolregistration.dto.RegistrationDto;
import com.works.schoolregistration.dto.RegistrationResponseDto;
import com.works.schoolregistration.exception.CourseRegistrationException;
import com.works.schoolregistration.exception.EntityNotFoundException;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.repository.CourseRepository;
import com.works.schoolregistration.repository.StudentRepository;
import com.works.schoolregistration.service.CourseRegistrationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService{

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public CourseRegistrationServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public RegistrationResponseDto registerCourse(RegistrationDto registrationDto) {

        Student student = studentRepository.findById(registrationDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        Course course = courseRepository.findById(registrationDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        Course isValidCourse = student.getCourses().stream()
                .filter(tempCourse -> registrationDto.getCourseId() == tempCourse.getId())
                .findAny().orElse(null);
        if(null != isValidCourse){
            throw new CourseRegistrationException("You are already enrolled in the course.");
        }

        if(student.getCourses().size() >= 5){
            throw new CourseRegistrationException("The limit has been exceeded. You can register for a maximum of 5 courses.");
        }

        List<Course> courseList = student.getCourses();
        courseList.add(course);
        student.setCourses(courseList);

        return RegistrationResponseDto.builder()
                .student(student)
                .courses(course)
                .build();
    }

    @Override
    @Transactional
    public RegistrationResponseDto cancelCourseRegistration(RegistrationDto registrationDto) {

        Student student = studentRepository.findById(registrationDto.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        Course course = courseRepository.findById(registrationDto.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course Not Found"));

        Course isValidCourse = student.getCourses().stream()
                .filter(tempCourse -> registrationDto.getCourseId() == tempCourse.getId())
                .findAny().orElse(null);
        if(null == isValidCourse){
            throw new CourseRegistrationException("You are not registered for the course.");
        }

        List<Course> courseList = student.getCourses();
        courseList.remove(course);
        student.setCourses(courseList);

        return RegistrationResponseDto.builder()
                .student(student)
                .courses(course)
                .build();
    }
}
