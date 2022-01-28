package com.works.schoolregistration.controller;

import com.works.schoolregistration.dto.RegistrationDto;
import com.works.schoolregistration.dto.RegistrationResponseDto;
import com.works.schoolregistration.service.CourseRegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/registration")
public class CourseRegistrationController {

    private final CourseRegistrationService courseRegistrationService;

    public CourseRegistrationController(CourseRegistrationService courseRegistrationService) {
        this.courseRegistrationService = courseRegistrationService;
    }

    @PostMapping("/add")
    private RegistrationResponseDto registerCourse(@RequestBody RegistrationDto registrationDto){

        return courseRegistrationService.registerCourse(registrationDto);
    }

    @PostMapping("/cancel")
    private RegistrationResponseDto cancelCourseRegistration(@RequestBody RegistrationDto registrationDto){

        return courseRegistrationService.cancelCourseRegistration(registrationDto);
    }
}
