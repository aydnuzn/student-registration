package com.works.schoolregistration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.schoolregistration.dto.StudentDto;
import com.works.schoolregistration.enumeration.CourseName;
import com.works.schoolregistration.enumeration.StudentGender;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.repository.StudentRepository;
import com.works.schoolregistration.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    ObjectMapper mapper;

    Course RECORD_COURSE = new Course(1L, CourseName.HISTORY, null);
    Student RECORD_STUDENT = new Student(1L, "Aydin", "Uzun", StudentGender.MALE, "100", null);

    @Test
    public void getAllCourseOnStudentById_success() throws Exception {

        mockMvc.perform(get("/students/courses/{studentNumber}",RECORD_STUDENT.getNumber())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllStudentNotAttendingCourse_success() throws Exception {

        mockMvc.perform(get("/students/notAttendingCourse")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentById_success() throws Exception {

        mockMvc.perform(get("/students/{studentId}",1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createStudentByStudentInfo_success() throws Exception {

        StudentDto studentDto = new StudentDto(
                RECORD_STUDENT.getName(), RECORD_STUDENT.getSurname(), RECORD_STUDENT.getGender(), RECORD_STUDENT.getNumber()
        );

        mockMvc.perform(post("/students")
                        .content(mapper.writeValueAsString(studentDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudentId_success() throws Exception {

        StudentDto studentDto = new StudentDto(
                RECORD_STUDENT.getName(), RECORD_STUDENT.getSurname(), RECORD_STUDENT.getGender(), RECORD_STUDENT.getNumber()
        );

        mockMvc.perform(put("/students/{studentId}",RECORD_STUDENT.getId())
                        .content(mapper.writeValueAsString(studentDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudentByNumber_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/{studentNumber}",RECORD_STUDENT.getNumber())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
