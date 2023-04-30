package edu.unimagdalena.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.entidades.Teacher;
import edu.unimagdalena.demo.repositories.TeacherRepository;

@DataJpaTest
public class TeacherRepositoryTests {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(null);
        teacher.setFirstName("Juan");
        teacher.setLastName("Perez");
        teacher.setCodigo("1234");
        teacher.setCourses(null);

        Teacher savedTeacher = teacherRepository.save(teacher);

        assertNotNull(savedTeacher.getId());
        assertEquals("Juan", savedTeacher.getFirstName());
        assertEquals("Perez", savedTeacher.getLastName());
        assertEquals("1234", savedTeacher.getCodigo());
    }

    @Test
    public void testFindAllTeachers() {
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Juan");
        teacher1.setLastName("Perez");
        teacher1.setCodigo("1234");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Maria");
        teacher2.setLastName("Gomez");
        teacher2.setCodigo("5678");
        teacherRepository.save(teacher2);

        List<Teacher> teachers = teacherRepository.findAll();

        assertEquals(2, teachers.size());
        assertEquals("Juan", teachers.get(0).getFirstName());
        assertEquals("Maria", teachers.get(1).getFirstName());
    }

    @Test
    public void testUpdateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Juan");
        teacher.setLastName("Perez");
        teacher.setCodigo("1234");
        teacherRepository.save(teacher);

        teacher.setFirstName("Pedro");
        teacherRepository.save(teacher);

        Teacher updatedTeacher = teacherRepository.findById(teacher.getId()).orElse(null);

        assertEquals("Pedro", updatedTeacher.getFirstName());
    }

    @Test
    public void testDeleteTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Juan");
        teacher.setLastName("Perez");
        teacher.setCodigo("1234");
        teacherRepository.save(teacher);

        teacherRepository.delete(teacher);

        Teacher deletedTeacher = teacherRepository.findById(teacher.getId()).orElse(null);

        assertNull(deletedTeacher);
    }

    @Test
    public void testFindTeacherById() {
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Juan");
        teacher1.setLastName("Perez");
        teacher1.setCodigo("1234");
        teacherRepository.save(teacher1);

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Maria");
        teacher2.setLastName("Gomez");
        teacher2.setCodigo("5678");
        teacherRepository.save(teacher2);

        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher1.getId());

        assertTrue(optionalTeacher.isPresent());
        assertEquals("Juan", optionalTeacher.get().getFirstName());
    }
}
