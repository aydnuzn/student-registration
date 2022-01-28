package com.works.schoolregistration.service;

import com.works.schoolregistration.dto.StudentDto;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;

import java.util.List;

public interface StudentService {

    List<Course> coursesAttendedByStudent(String studentNumber);

    List<Student> allStudentsByNotAttendAnyCourse();

    Student getStudent(Long studentId);

    Student createStudent(StudentDto studentDto);

    Student updateStudent(Long studentId, StudentDto studentDto);

    Student deleteStudent(String studentNumber);
}
