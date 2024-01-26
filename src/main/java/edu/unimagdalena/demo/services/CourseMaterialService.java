package edu.unimagdalena.demo.services;
import java.util.List;
import java.util.Optional;

import edu.unimagdalena.demo.entities.CourseMaterial;

public interface CourseMaterialService {
    List<CourseMaterial> findAll();
    Optional<CourseMaterial> find(Long id);
    CourseMaterial create(CourseMaterial CourseMaterial);
    Optional<CourseMaterial> update(Long id, CourseMaterial newCourse);
    void delete(Long id);
}
