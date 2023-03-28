package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.entidades.CourseMaterial;
import edu.unimagdalena.demo.repositories.CourseMaterialRepository;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    @Autowired
    public CourseMaterialServiceImpl(CourseMaterialRepository courseMaterialRepository) {
        this.courseMaterialRepository = courseMaterialRepository;
    }

    @Override
    public List<CourseMaterial> findAll() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public Optional<CourseMaterial> find(Long id) {
        return courseMaterialRepository.findById(id);
    }

    @Override
    public CourseMaterial create(CourseMaterial courseMaterial) {
        CourseMaterial newCourseMaterial = new CourseMaterial(null, courseMaterial.getUrl(), courseMaterial.getCourse());
        return courseMaterialRepository.save(newCourseMaterial);
    }

    @Override
    public Optional<CourseMaterial> update(Long id, CourseMaterial newCourse) {
        return courseMaterialRepository.findById(id)
        .map(courseMaterialInDB -> {
            courseMaterialInDB.setUrl(newCourse.getUrl());
            courseMaterialInDB.setCourse(newCourse.getCourse());
            return courseMaterialRepository.save(courseMaterialInDB);
        });
    }

    @Override
    public void delete(Long id) {
        courseMaterialRepository.deleteById(id);
    }

    /*@Override
    public Optional<CourseMaterial> assignCourse(Long id, Course course) {
        return courseMaterialRepository.findById(id)
        .map(courseMaterialInDB -> {
            courseMaterialInDB.setCourse(course);
            return courseMaterialRepository.save(courseMaterialInDB);
        });
    }
    */
    
}
