package com.works.schoolregistration.projection;

import com.works.schoolregistration.enumeration.StudentGender;

public interface StudentInfo {
    Long getId();

    String getName();

    String getSurname();

    StudentGender getGender();

    String getNumber();
}
