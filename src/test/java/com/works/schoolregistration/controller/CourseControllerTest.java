package com.works.schoolregistration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.schoolregistration.dto.CourseDto;
import com.works.schoolregistration.enumeration.CourseName;
import com.works.schoolregistration.enumeration.StudentGender;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.repository.CourseRepository;
import com.works.schoolregistration.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseRepository courseRepository;

    @Autowired
    ObjectMapper mapper;

    Course RECORD_COURSE = new Course(1L, CourseName.HISTORY, null);
    Student RECORD_STUDENT = new Student(1L, "Aydin", "Uzun", StudentGender.MALE, "100", null);

    @Test
    public void getAllCourseRecord_success() throws Exception {

        mockMvc.perform(get("/courses/allCourses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentsOnCourse_success() throws Exception {

        mockMvc.perform(get("/courses/students/{courseName}}",RECORD_COURSE.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getCourseById_success() throws Exception {

        mockMvc.perform(get("/courses/{courseId}",RECORD_COURSE.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createCourseByCourseName_success() throws Exception {

        CourseDto courseDto = new CourseDto(RECORD_COURSE.getName());

        mockMvc.perform(post("/courses")
                        .content(mapper.writeValueAsString(courseDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudentId_success() throws Exception {

        CourseDto courseDto = new CourseDto(RECORD_COURSE.getName());

        mockMvc.perform(put("/courses/{courseId}",RECORD_COURSE.getId())
                        .content(mapper.writeValueAsString(courseDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCourseById_success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/courses/{courseId}",RECORD_COURSE.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
