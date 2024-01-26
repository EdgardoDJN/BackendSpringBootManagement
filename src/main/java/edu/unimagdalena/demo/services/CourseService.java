package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.demo.entities.Course;

public interface CourseService {
    List<Course> findAll();
    Optional<Course> find(Long id);
    //creamos un curso que no va a tener profesor asignado
    Course create(Course course);
    Optional<Course> update(Long id, Course newCourse);
    void delete(Long id);
    List<Course> findCourseByName(String name);
}
