package edu.unimagdalena.demo.api.dto;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entidades.Course;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setName(course.getName());
        dto.setTeacher(course.getTeacher());
        dto.setStudents(course.getStudents());
        return dto;
    }
    public CourseCreateDto toCreateDto(Course course) {
        CourseCreateDto dto = new CourseCreateDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setTeacher(course.getTeacher());
        dto.setStudents(course.getStudents());
        return dto;
    }
    public Course toEntity(CourseDto dto) {
        Course entity = new Course();
        entity.setName(dto.getName());
        entity.setTeacher(dto.getTeacher());
        entity.setStudents(dto.getStudents());
        return entity;
    }
    public Course toEntity(CourseCreateDto dto) {
        Course entity = new Course();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTeacher(dto.getTeacher());
        entity.setStudents(dto.getStudents());
        return entity;
    }
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
    public CourseDto toDto(Optional<Course> course) {
        CourseDto dto = new CourseDto();
        dto.setName(course.get().getName());
        dto.setTeacher(course.get().getTeacher());
        dto.setStudents(course.get().getStudents());
        return dto;
    }
    public CourseCreateDto toCreateDto(Optional<Course> course) {
        CourseCreateDto dto = new CourseCreateDto();
        dto.setId(course.get().getId());
        dto.setName(course.get().getName());
        dto.setTeacher(course.get().getTeacher());
        dto.setStudents(course.get().getStudents());
        return dto;
    }
        
}

