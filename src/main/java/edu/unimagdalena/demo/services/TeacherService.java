package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import edu.unimagdalena.demo.entidades.Teacher;

public interface TeacherService {
    //El simple hecho de ser una interfaz ya lo hace publico por eso no es necesario colocarla aqui abajo
    //Operaciones que el controlador va a poder acceder
    Teacher create(Teacher teacher);
    Optional<Teacher> update(Long id, Teacher teacher);
    List<Teacher> findAll();
    Optional<Teacher> find(Long id);
    void delete(Long id);
}
