package com.works.schoolregistration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class RegistrationDto {

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;
}
