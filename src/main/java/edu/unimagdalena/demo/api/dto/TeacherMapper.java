package edu.unimagdalena.demo.api.dto;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entities.Teacher;

@Component
public class TeacherMapper {
    //Pasar de una forma a otra forma
    public TeacherDto toDto(Teacher teacher)
    {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setCodigo(teacher.getCodigo());
        return teacherDto;
    }
    public Teacher toEntity(TeacherDto teacherDto)
    {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setCodigo(teacherDto.getCodigo());
        return teacher;
    }
    public TeacherCreationDto toTeacherCreationDto(Teacher teacher){
        TeacherCreationDto teacherCreationDto = new TeacherCreationDto();
        teacherCreationDto.setId(teacher.getId());
        teacherCreationDto.setFirstName(teacher.getFirstName());
        teacherCreationDto.setLastName(teacher.getLastName());
        teacherCreationDto.setCodigo(teacher.getCodigo());

        return teacherCreationDto;
    }
    public TeacherDto toDto(Optional<Teacher> teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setFirstName(teacher.get().getFirstName());
        teacherDto.setLastName(teacher.get().getLastName());
        teacherDto.setCodigo(teacher.get().getCodigo());
        return teacherDto;
    }
    public TeacherCreationDto toCreationDto(Optional<Teacher> savedTeacher)
    {
        TeacherCreationDto teacherCreationDto = new TeacherCreationDto();
        teacherCreationDto.setId(savedTeacher.get().getId());
        teacherCreationDto.setFirstName(savedTeacher.get().getFirstName());
        teacherCreationDto.setLastName(savedTeacher.get().getLastName());
        teacherCreationDto.setCodigo(savedTeacher.get().getCodigo());
        return teacherCreationDto;
    }
    public TeacherCourseDto toTeacherCourseDto(Teacher teacher) {
        TeacherCourseDto teacherCourseDto = new TeacherCourseDto();
       teacherCourseDto.setId(teacher.getId());
        teacherCourseDto.setFirstName(teacher.getFirstName());
        teacherCourseDto.setLastName(teacher.getLastName());
        teacherCourseDto.setCodigo(teacher.getCodigo());
        Set<CourseDto2> courseDtos = teacher.getCourses().stream().map(course -> {
            CourseDto2 courseDto = new CourseDto2();
            courseDto.setId(course.getId());
            courseDto.setName(course.getName());
            return courseDto;
        }).collect(Collectors.toSet());
        teacherCourseDto.setCourses(courseDtos);
        return teacherCourseDto;
    }
    public Teacher toEntity(TeacherCourseDto teacherCourseDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherCourseDto.getId());
        teacher.setFirstName(teacherCourseDto.getFirstName());
        teacher.setLastName(teacherCourseDto.getLastName());
        teacher.setCodigo(teacherCourseDto.getCodigo());
        return teacher;
    }
    public TeacherDto toDto2(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setFirstName(teacher.getFirstName());
        dto.setLastName(teacher.getLastName());
        dto.setCodigo(teacher.getCodigo());
        return dto;
    }
    
}
