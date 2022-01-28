package com.works.schoolregistration.controller;

import com.works.schoolregistration.dto.CourseDto;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.service.CourseService;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value = "/students/{courseName}")
    private List<Student> studentsOnCourse(@PathVariable String courseName){

        return courseService.studentsOnCourse(courseName);
    }

    @GetMapping("/allCourses")
    private List<Course> getAllCourses(){

        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    private Course getCourse(@PathVariable Long courseId){

        return courseService.getCourse(courseId);
    }

    @PostMapping
    private Course createCourse(@RequestBody @Valid CourseDto courseDto){

        return courseService.createCourse(courseDto);
    }

    @PutMapping("/{courseId}")
    private Course updateCourse(@PathVariable Long courseId, @RequestBody @Valid CourseDto courseDto){

        return courseService.updateCourse(courseId, courseDto);
    }

    @DeleteMapping("/{courseId}")
    private Course deleteCourse(@PathVariable Long courseId){

        return courseService.deleteCourse(courseId);
    }
}
