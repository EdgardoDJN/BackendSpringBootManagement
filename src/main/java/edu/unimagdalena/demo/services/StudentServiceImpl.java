package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.entidades.Student;
import edu.unimagdalena.demo.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student create(Student student) {
        Student copy=
        new Student(null, student.getLastName(), student.getFirstname(), student.getCodigo(), student.getBirthDate(), student.getGender(), null);
        return studentRepository.save(copy);
    }

    @Override
    public Optional<Student> update(Long id, Student student) {
        return studentRepository.findById(id)
        .map(studentInDB -> {
            studentInDB.setFirstname(student.getFirstname());
            studentInDB.setLastName(student.getLastName());
            studentInDB.setCodigo(student.getCodigo());
            studentInDB.setBirthDate(student.getBirthDate());
            studentInDB.setGender(student.getGender());
            return studentRepository.save(studentInDB);
        });
    }

    @Override
    public List<Student> findAll() {
       return studentRepository.findAll();
    }

    @Override
    public Optional<Student> find(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
    
}
