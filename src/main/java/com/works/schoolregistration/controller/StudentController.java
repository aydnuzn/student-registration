package com.works.schoolregistration.controller;

import com.works.schoolregistration.dto.StudentDto;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/courses/{studentNumber}")
    private List<Course> coursesAttendedByStudent(@PathVariable String studentNumber){

        return studentService.coursesAttendedByStudent(studentNumber);
    }

    @GetMapping("/notAttendingCourse")
    private List<Student> allStudentsByNotAttendAnyCourse(){

        return studentService.allStudentsByNotAttendAnyCourse();
    }

    @GetMapping("/{studentId}")
    private Student getStudent(@PathVariable Long studentId){

        return studentService.getStudent(studentId);
    }

    @PostMapping
    private Student createStudent(@RequestBody @Valid StudentDto studentDto){

        return studentService.createStudent(studentDto);
    }

    @PutMapping("/{studentId}")
    private Student updateStudent(@PathVariable Long studentId, @RequestBody @Valid StudentDto studentDto){

        return studentService.updateStudent(studentId, studentDto);
    }

    @DeleteMapping("/{studentNumber}")
    private Student deleteStudent(@PathVariable String studentNumber){

        return studentService.deleteStudent(studentNumber);
    }

}
