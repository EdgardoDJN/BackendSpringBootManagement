package edu.unimagdalena.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.demo.entidades.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    //La anotación @Repository es una anotación de Spring que indica que la clase es un repositorio.
    //Un repositorio es una clase que proporciona una capa de abstracción entre la capa de persistencia y la capa de negocios.
    //La capa de persistencia se encarga de acceder y manipular los datos almacenados en la base de datos.
    //La capa de negocios se encarga de la lógica de negocio de la aplicación.

}