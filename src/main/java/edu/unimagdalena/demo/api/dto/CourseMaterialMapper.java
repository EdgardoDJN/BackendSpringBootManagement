package edu.unimagdalena.demo.api.dto;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entidades.CourseMaterial;
@Component
public class CourseMaterialMapper {
    public CourseMaterialDto toDto(CourseMaterial courseMaterial) {
        CourseMaterialDto dto = new CourseMaterialDto();
        dto.setUrl(courseMaterial.getUrl());
        CourseDto courseDto = new CourseDto();
        courseDto.setName(courseMaterial.getCourse().getName());
        TeacherDto teacher = new TeacherDto(courseMaterial.getCourse().getTeacher().getFirstName(), courseMaterial.getCourse().getTeacher().getLastName(), courseMaterial.getCourse().getTeacher().getCodigo());
        courseDto.setTeacher(teacher);
        Set<StudentDto> students = courseMaterial.getCourse().getStudents().stream()
                                          .map(student -> new StudentDto(student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                                          .collect(Collectors.toSet());
        courseDto.setStudents(students);
        dto.setCourse(courseDto);
        return dto;
    }
    public CourseMaterialCreateDto toCreateDto(CourseMaterial courseMaterial) {
        CourseMaterialCreateDto dto = new CourseMaterialCreateDto();
        dto.setId(courseMaterial.getId());
        dto.setUrl(courseMaterial.getUrl());
        CourseCreateDto coursecreateDto = new CourseCreateDto();
        coursecreateDto.setId(courseMaterial.getCourse().getId());
        coursecreateDto.setName(courseMaterial.getCourse().getName());
        TeacherCreationDto teacher = new TeacherCreationDto(courseMaterial.getCourse().getTeacher().getId(), courseMaterial.getCourse().getTeacher().getFirstName(), courseMaterial.getCourse().getTeacher().getLastName(), courseMaterial.getCourse().getTeacher().getCodigo());
        coursecreateDto.setTeacher(teacher);
        Set<StudentCreateDto> students = courseMaterial.getCourse().getStudents().stream()
                                          .map(student -> new StudentCreateDto(student.getId(),student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                                          .collect(Collectors.toSet());
        coursecreateDto.setStudents(students);
        dto.setCourse(coursecreateDto);
        return dto;
    }
    public CourseMaterialCreateDto toCreateDto(Optional<CourseMaterial> courseMaterial) {
        CourseMaterialCreateDto prueba = new CourseMaterialCreateDto();
        prueba.setId(courseMaterial.get().getId());
        prueba.setUrl(courseMaterial.get().getUrl());
        CourseCreateDto coursecreateDto = new CourseCreateDto();
        coursecreateDto.setId(courseMaterial.get().getCourse().getId());
        coursecreateDto.setName(courseMaterial.get().getCourse().getName());
        TeacherCreationDto teacher = new TeacherCreationDto(courseMaterial.get().getCourse().getTeacher().getId(), courseMaterial.get().getCourse().getTeacher().getFirstName(), courseMaterial.get().getCourse().getTeacher().getLastName(), courseMaterial.get().getCourse().getTeacher().getCodigo());
        coursecreateDto.setTeacher(teacher);
        Set<StudentCreateDto> students = courseMaterial.get().getCourse().getStudents().stream()
                                          .map(student -> new StudentCreateDto(student.getId(),student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                                          .collect(Collectors.toSet());
        coursecreateDto.setStudents(students);
        prueba.setCourse(coursecreateDto);
        return prueba;
    }
    /*public CourseMaterial toEntity(CourseMaterialDto dto) {
        CourseMaterial entity = new CourseMaterial();
        entity.setUrl(dto.getUrl());
        entity.setCourse(dto.getCourse());
        return entity;
    }
    /* 
    public CourseMaterial toEntity(CourseMaterialCreateDto dto) {
        CourseMaterial entity = new CourseMaterial();
        entity.setUrl(dto.getUrl());
        entity.setCourse(dto.getCourse());
        return entity;
    }
    */
    /*public CourseMaterialDto toDto(Optional<CourseMaterial> courseMaterial) {
        CourseMaterialDto dto = new CourseMaterialDto();
        dto.setUrl(courseMaterial.get().getUrl());
        dto.setCourse(courseMaterial.get().getCourse());
        return dto;
    }
    */

    
}
