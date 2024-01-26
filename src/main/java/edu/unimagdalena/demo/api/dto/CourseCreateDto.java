package edu.unimagdalena.demo.api.dto;


import java.util.Set;

import edu.unimagdalena.demo.entities.Student;
import edu.unimagdalena.demo.entities.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDto {
    private Long id;
    private String name;
    private TeacherCreationDto teacher;
    private Set<StudentCreateDto> students;
}
