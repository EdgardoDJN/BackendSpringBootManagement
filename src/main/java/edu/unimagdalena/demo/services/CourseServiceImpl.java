package edu.unimagdalena.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.repositories.CourseRepository;
import edu.unimagdalena.demo.repositories.StudentRepository;

@Service
public class CourseServiceImpl implements CourseService {
    //En un servicio puedo tener mas de un repositorio si asi lo requiero
    @Autowired
    private final CourseRepository courseRepository;

    //Este en dado caso que el estudiante no exista
    @Autowired
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> find(Long id) {
       return courseRepository.findById(id);
    }

    @Override
    public Course create(Course course) {
        Course newCourse = new Course(null,course.getName(), course.getTeacher(), course.getStudents(), null);
        return courseRepository.save(newCourse);
    }

    @Override
    public Optional<Course> update(Long id, Course newCourse) {
        //Porque no utilizo courseMaterial
       return courseRepository.findById(id)
        .map(courseInDB -> {
            courseInDB.setName(newCourse.getName());
            courseInDB.setTeacher(newCourse.getTeacher());
            courseInDB.setStudents(newCourse.getStudents());

            return courseRepository.save(courseInDB);
       });
    }

    @Override
    public void delete(Long id) {
       courseRepository.deleteById(id);
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    /*@Override
    public Optional<Course> assignTeacher(Long id, Teacher teacher) {
        //optional para no retornar null
        return courseRepository.findById(id)
        .map(course -> {
            course.setTeacher(teacher);
            return courseRepository.save(course);
        });
    }
    */

    /*@Override
    public Optional<Course> addStudent(Long idCourse, Student newStudent) {
        //Estamos utilizando dos repositorios diferentes dentro de un mismo servicio
        return courseRepository.findById(idCourse)
            .map(course -> {
                Optional<Student> studentInDb = studentRepository.findByCodigo(newStudent.getCodigo());
                if(studentInDb.isPresent()){
                    course.getStudents().add(newStudent);
                }else{
                    Student student = studentRepository.save(newStudent);
                    course.getStudents().add(student);
                }
              
                return courseRepository.save(course);
              

            });
    }
    */
}
