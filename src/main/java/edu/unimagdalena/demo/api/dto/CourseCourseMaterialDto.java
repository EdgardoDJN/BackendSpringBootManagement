package edu.unimagdalena.demo.api.dto;

import java.util.Set;

import edu.unimagdalena.demo.entidades.CourseMaterial;
import edu.unimagdalena.demo.entidades.Student;
import edu.unimagdalena.demo.entidades.Teacher;

public class CourseCourseMaterialDto {
    private String name;
    private Teacher teacher;
    private Set<Student> students;
    private CourseMaterial courseMaterial;
}
