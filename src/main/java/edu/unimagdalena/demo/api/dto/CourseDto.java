package edu.unimagdalena.demo.api.dto;

import java.util.Set;

import edu.unimagdalena.demo.entidades.Student;
import edu.unimagdalena.demo.entidades.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String name;
    private TeacherDto teacher;
    private Set<StudentDto> students;
}
