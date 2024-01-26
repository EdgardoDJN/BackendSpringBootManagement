package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.demo.entities.Student;

public interface StudentService {
    Student create(Student student);
    Optional<Student> update(Long id, Student student);
    List<Student> findAll();
    Optional<Student> find(Long id);
    void delete(Long id);
}
