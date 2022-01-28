package com.works.schoolregistration.service;

import com.works.schoolregistration.dto.RegistrationDto;
import com.works.schoolregistration.dto.RegistrationResponseDto;

public interface CourseRegistrationService {

    RegistrationResponseDto registerCourse(RegistrationDto registrationDto);

    RegistrationResponseDto cancelCourseRegistration(RegistrationDto registrationDto);
}
