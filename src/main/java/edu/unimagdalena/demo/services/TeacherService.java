package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.demo.entities.Teacher;

public interface TeacherService {
    Teacher create(Teacher teacher);
    Optional<Teacher> update(Long id, Teacher teacher);
    List<Teacher> findAll();
    Optional<Teacher> find(Long id);
    void delete(Long id);
}
