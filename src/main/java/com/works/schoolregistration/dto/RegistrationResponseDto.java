package com.works.schoolregistration.dto;

import com.works.schoolregistration.model.Course;
import com.works.schoolregistration.model.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponseDto {

    private Student student;
    private Course courses;

}
