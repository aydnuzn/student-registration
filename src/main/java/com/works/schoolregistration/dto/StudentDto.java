package com.works.schoolregistration.dto;

import com.works.schoolregistration.enumeration.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    @NotEmpty
    @Size(min = 2, max = 64)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 64)
    private String surname;

    @NotNull
    private StudentGender gender;

    @NotEmpty
    @Size(min = 1, max = 16)
    private String number;
}
