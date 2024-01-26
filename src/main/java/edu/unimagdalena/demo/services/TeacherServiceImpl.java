package edu.unimagdalena.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.entities.Teacher;
import edu.unimagdalena.demo.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        Teacher copy = new Teacher(null, 
                                teacher.getFirstName(), 
                                teacher.getLastName(), 
                                teacher.getCodigo(), 
                                null);
        return teacherRepository.save(copy);
    }

    @Override
    public Optional<Teacher> update(Long id, Teacher newTeacher) {
        // Buscamos a ese que esta en la base de datos
        return teacherRepository.findById(id)
        .map(oldTeacher -> {
            Teacher teacher = oldTeacher.updateWith(newTeacher);
            return teacherRepository.save(teacher);
        });
        /*Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(optionalTeacher.isPresent()){
            Teacher oldTeacher = optionalTeacher.get();
            Teacher teacher = oldTeacher.updateWith(newTeacher);
            return Optional.of(teacherRepository.save(teacher));
        }
        return optionalTeacher;
        */
        
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> find(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);  
    }
}
