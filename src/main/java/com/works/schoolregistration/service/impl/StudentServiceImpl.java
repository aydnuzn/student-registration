package com.works.schoolregistration.service.impl;

import com.works.schoolregistration.dto.StudentDto;
import com.works.schoolregistration.exception.EntityNotFoundException;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.projection.StudentInfo;
import com.works.schoolregistration.repository.StudentRepository;
import com.works.schoolregistration.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> coursesAttendedByStudent(String studentNumber) {

        Student student = studentRepository.findByNumberEquals(studentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        List<Course> courseList = student.getCourses();

        return courseList;
    }

    @Override
    public List<Student> allStudentsByNotAttendAnyCourse() {

        List<Student> studentList = new ArrayList<>();
        for(StudentInfo studentInfo : studentRepository.AllStudentsNotAttendCourse()){
            Student student = Student.builder()
                    .id(studentInfo.getId())
                    .name(studentInfo.getName())
                    .surname(studentInfo.getSurname())
                    .gender(studentInfo.getGender())
                    .number(studentInfo.getNumber())
                    .build();
            studentList.add(student);
        }

        return studentList;
    }

    @Override
    public Student getStudent(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        return student;
    }

    @Override
    public Student createStudent(StudentDto studentDto) {

        Student student = Student.builder()
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .gender(studentDto.getGender())
                .number(studentDto.getNumber())
                .courses(null)
                .build();
        student = studentRepository.save(student);

        return student;
    }

    @Override
    public Student updateStudent(Long studentId, StudentDto studentDto) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        student.setGender(studentDto.getGender());
        student.setNumber(studentDto.getNumber());
        student = studentRepository.saveAndFlush(student);

        return student;
    }

    @Override
    public Student deleteStudent(String studentNumber) {

        Student student = studentRepository.findByNumberEquals(studentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student Not Found"));
        studentRepository.delete(student);

        return student;
    }

}
