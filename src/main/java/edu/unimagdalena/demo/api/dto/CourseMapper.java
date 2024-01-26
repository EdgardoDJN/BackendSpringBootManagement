package edu.unimagdalena.demo.api.dto;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entities.Course;
import edu.unimagdalena.demo.entities.Student;
import edu.unimagdalena.demo.entities.Teacher;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setName(course.getName());
        TeacherDto teacher2 = new TeacherDto(course.getTeacher().getFirstName(), course.getTeacher().getLastName(), course.getTeacher().getCodigo());
        dto.setTeacher(teacher2);
        Set<StudentDto> students = course.getStudents().stream()
                                          .map(student -> new StudentDto(student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                                          .collect(Collectors.toSet());
        dto.setStudents(students);
        return dto;
    }
    public CourseCreateDto toCreateDto(Course course) {
        CourseCreateDto dto = new CourseCreateDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        TeacherCreationDto teacher2 = new TeacherCreationDto(course.getTeacher().getId(), course.getTeacher().getFirstName(), course.getTeacher().getLastName(), course.getTeacher().getCodigo());
        dto.setTeacher(teacher2);
        Set<StudentCreateDto> students = course.getStudents().stream()
                                          .map(student -> new StudentCreateDto(student.getId(), student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                                          .collect(Collectors.toSet());
        dto.setStudents(students);
        return dto;
    }
    public Course toEntity(CourseDto dto) {
        Course entity = new Course();
        entity.setName(dto.getName());
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.getTeacher().getFirstName());
        teacher.setLastName(dto.getTeacher().getLastName());
        teacher.setCodigo(dto.getTeacher().getCodigo());
        entity.setTeacher(teacher);
        Set<Student> students = dto.getStudents().stream()
                                    .map(student -> new Student(null, student.getLastName(), student.getFirstName(), student.getCodigo(), student.getBirthDate(), student.getGender(), null))
                                    .collect(Collectors.toSet());
        entity.setStudents(students);
        return entity;
    }
    
    
    
    /*public Course toEntity(CourseCreateDto dto) {
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTeacher(dto.getTeacher());
        entity.setStudents(dto.getStudents());
        return entity;
    }
    */
    public List<CourseDto> toDto(List<Course> courses) {
        /*Set<CourseDto> dtos = new HashSet<>();
        courses.stream().forEach(course -> {
            dtos.add(toDto(course));
        });
        return dtos;
        */
        //Mas funcional
        return courses.stream()
                       .map(course -> toDto(course))
                       .collect(Collectors.toList());
        
    }
    /*public CourseDto toDto(Optional<Course> course) {
        CourseDto dto = new CourseDto();
        dto.setName(course.get().getName());
        dto.setTeacher(course.get().getTeacher());
        dto.setStudents(course.get().getStudents());
        return dto;
    }
    */
    public CourseCreateDto toCreateDto(Optional<Course> course) {
        CourseCreateDto dto = new CourseCreateDto();
        dto.setId(course.get().getId());
        dto.setName(course.get().getName());
        TeacherCreationDto teacher2 = new TeacherCreationDto(course.get().getTeacher().getId(), course.get().getTeacher().getFirstName(), course.get().getTeacher().getLastName(), course.get().getTeacher().getCodigo());
        dto.setTeacher(teacher2);
        Set<StudentCreateDto> students = course.get().getStudents().stream()
                .map(student -> new StudentCreateDto(student.getId(),student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender()))
                .collect(Collectors.toSet());
        dto.setStudents(students);
        return dto;
    }
    
        
}

