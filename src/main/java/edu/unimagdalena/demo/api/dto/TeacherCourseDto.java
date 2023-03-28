package edu.unimagdalena.demo.api.dto;

import java.util.Set;

import lombok.Data;

@Data
public class TeacherCourseDto {
    //Dentro de un Dto puedo llamar a otro Dto
    private Long id;
    private String firstName;
    private String lastName;
    private String codigo;
    Set<CourseCreateDto> courses;
}
