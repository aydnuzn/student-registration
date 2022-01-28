package com.works.schoolregistration.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.works.schoolregistration.enumeration.CourseName;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private CourseName name;

    @JsonBackReference
    @ManyToMany(mappedBy = "courses")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Student> students;

}
