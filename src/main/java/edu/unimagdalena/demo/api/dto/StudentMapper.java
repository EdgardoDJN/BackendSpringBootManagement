package edu.unimagdalena.demo.api.dto;


import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entidades.Student;


@Component
public class StudentMapper {

    public Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student entity = new Student();
        entity.setFirstname(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCodigo(dto.getCodigo());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        return entity;
    }
    public  StudentCreateDto toCreateDto(Student entity) {
        StudentCreateDto dto = new StudentCreateDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstname());
        dto.setLastName(entity.getLastName());
        dto.setCodigo(entity.getCodigo());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        return dto;
    }

    public  StudentDto toDto(Student entity) {
        StudentDto dto = new StudentDto();
        dto.setFirstName(entity.getFirstname());
        dto.setLastName(entity.getLastName());
        dto.setCodigo(entity.getCodigo());
        dto.setBirthDate(entity.getBirthDate());
        dto.setGender(entity.getGender());
        return dto;
    }
    public StudentCreateDto toCreateDto(Optional<Student> savedStudent) {
        StudentCreateDto dto = new StudentCreateDto();
        dto.setId(savedStudent.get().getId());
        dto.setFirstName(savedStudent.get().getFirstname());
        dto.setLastName(savedStudent.get().getLastName());
        dto.setCodigo(savedStudent.get().getCodigo());
        dto.setBirthDate(savedStudent.get().getBirthDate());
        dto.setGender(savedStudent.get().getGender());
        return dto;
    }
    public StudentCourseDto toCourseDto(Student student) {
        StudentCourseDto studentCourseDto = new StudentCourseDto();
        studentCourseDto.setFirstName(student.getFirstname());
        studentCourseDto.setLastName(student.getLastName());
        studentCourseDto.setCodigo(student.getCodigo());
        studentCourseDto.setBirthDate(student.getBirthDate());
        studentCourseDto.setGender(student.getGender());
        Set<CourseDto2> courses = student.getCourses().stream()
                .map(course -> new CourseDto2(course.getId(), course.getName()))
                .collect(Collectors.toSet());
        studentCourseDto.setCourses(courses);
        return studentCourseDto;
    }
    public Student toEntity(StudentCourseDto dto) {
        Student entity = new Student();
        entity.setFirstname(dto.getFirstName());
        entity.setFirstname(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCodigo(dto.getCodigo());
        entity.setBirthDate(dto.getBirthDate());
        entity.setGender(dto.getGender());
        return entity;
    }
}

