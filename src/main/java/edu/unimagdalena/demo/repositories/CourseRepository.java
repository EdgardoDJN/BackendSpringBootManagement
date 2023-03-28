package edu.unimagdalena.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.demo.entidades.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //con inner join tambien funciona
    //Query methods CUANDO tengamos funciones de agregación en la consulta
    //rama repository layer va a estar la parte del codigo nuev trabajado
    //@Query("SELECT c FROM Course c JOIN c.teacher t WHERE t.firstName = :firstName")
    List<Course> findByName(String name);
}
