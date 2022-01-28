package com.works.schoolregistration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.works.schoolregistration.dto.RegistrationDto;
import com.works.schoolregistration.enumeration.CourseName;
import com.works.schoolregistration.enumeration.StudentGender;
import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import com.works.schoolregistration.service.CourseRegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseRegistrationController.class)
public class CourseRegistrationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CourseRegistrationService courseRegistrationService;

    @Autowired
    ObjectMapper mapper;

    Course RECORD_COURSE = new Course(1L, CourseName.HISTORY, null);
    Student RECORD_STUDENT = new Student(1L, "Aydin", "Uzun", StudentGender.MALE, "100", null);

    @Test
    public void studentRegisterCourseRecord_success() throws Exception {

        RegistrationDto registrationDto = new RegistrationDto(RECORD_STUDENT.getId(), RECORD_COURSE.getId());

        mockMvc.perform(post("/registration/add")
                        .content(mapper.writeValueAsString(registrationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void studentCancelCourseRecord_success() throws Exception {

        RegistrationDto registrationDto = new RegistrationDto(RECORD_STUDENT.getId(), RECORD_COURSE.getId());

        mockMvc.perform(post("/registration/cancel")
                        .content(mapper.writeValueAsString(registrationDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

}
