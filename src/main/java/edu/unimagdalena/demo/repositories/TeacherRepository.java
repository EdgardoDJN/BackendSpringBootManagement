package edu.unimagdalena.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unimagdalena.demo.entidades.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {


}