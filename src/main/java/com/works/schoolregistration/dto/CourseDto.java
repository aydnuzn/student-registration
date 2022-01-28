package com.works.schoolregistration.dto;

import com.works.schoolregistration.enumeration.CourseName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    @NotNull
    private CourseName name;
}
