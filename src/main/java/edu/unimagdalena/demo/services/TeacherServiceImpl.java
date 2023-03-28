package edu.unimagdalena.demo.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unimagdalena.demo.entidades.Teacher;
import edu.unimagdalena.demo.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {
    //Por el hecho de ser final toca crear un constructor
    private final TeacherRepository teacherRepository;

    //Inyectamos el repositorio por constructor para poder usarlo sin nigun problema abajo
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher create(Teacher teacher) {
        //Este es logica de negocio para crear un profesor
        //Hacemos esto para evitar un aliasing
        //cuando se crea un profesor la primera ves no debe estar asignado a ningun curso
        //por eso el getCourse es null
        //id no existe todavia
        Teacher copy = new Teacher(null, 
                                teacher.getFirstName(), 
                                teacher.getLastName(), 
                                teacher.getCodigo(), 
                                null);
        return teacherRepository.save(copy);
        //Aqui crea un nuevo registro
    }

    @Override
    public Optional<Teacher> update(Long id, Teacher newTeacher) {
        // Buscamos a ese que esta en la base de datos
        //En este como tenemos registro solo se actualiza, importante saber del save
        //otra forma de hacerlo 
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
