package edu.unimagdalena.demo.api.dto;


import java.util.Set;

import edu.unimagdalena.demo.entidades.Student;
import edu.unimagdalena.demo.entidades.Teacher;
import lombok.Data;

@Data
public class CourseCreateDto {
    private Long id;
    private String name;
    private Teacher teacher;
    private Set<Student> students;
}
