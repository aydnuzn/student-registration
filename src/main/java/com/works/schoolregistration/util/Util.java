package com.works.schoolregistration.util;

import com.works.schoolregistration.enumeration.CourseName;

public class Util {

    // Check if String variable exists in Enum
    public static Boolean checkStringInsideEnum(String name) {
        for (CourseName courseName : CourseName.values()) {
            if (courseName.name().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
