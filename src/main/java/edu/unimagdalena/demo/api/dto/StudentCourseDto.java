package edu.unimagdalena.demo.api.dto;

import java.time.LocalDate;
import java.util.Set;

import edu.unimagdalena.demo.entidades.Gender;
import lombok.Data;

@Data
public class StudentCourseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String codigo;
    private LocalDate birthDate;
    private Gender gender;
    Set<CourseDto2> courses;
}
