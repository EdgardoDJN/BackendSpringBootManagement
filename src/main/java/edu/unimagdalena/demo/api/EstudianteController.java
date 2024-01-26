package edu.unimagdalena.demo.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import edu.unimagdalena.demo.api.dto.CourseMapper;
import edu.unimagdalena.demo.api.dto.StudentCreateDto;
import edu.unimagdalena.demo.api.dto.StudentDto;
import edu.unimagdalena.demo.api.dto.StudentMapper;
import edu.unimagdalena.demo.entities.Student;
import edu.unimagdalena.demo.services.StudentService;
import edu.unimagdalena.demo.exceptions.DuplicateCodigoException;
import edu.unimagdalena.demo.exceptions.StudentNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class EstudianteController {

    //Segun la estructura del codigo que hemos trabajado el controlador debe contener estos metodos, ya si queremos saber los cursos de un estudiante debemos crear un metodo que nos permita hacer eso desde Repository, services y luego controller
    private final StudentService studentService;
    
    private final StudentMapper studentMapper;
    
    private final CourseMapper courseMapper;
    
    
    
    public EstudianteController(StudentService studentService, StudentMapper studentMapper, CourseMapper courseMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
    }
    @GetMapping("/students")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentService.findAll();
        if(students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            List<StudentDto> studentsDto = students.stream().map(studentMapper::toDto).collect(Collectors.toList()) ;
            return new ResponseEntity<>(studentsDto, HttpStatus.OK);
        }
    }
    /* 
    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll(){
        List<Student> teachers = studentService.findAll();
        return ResponseEntity.ok().body(teachers);
        
    }
    */
    @GetMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentCreateDto> findById(@PathVariable("id") Long id){
		StudentCreateDto student = studentService.find(id)
                    .map(s -> studentMapper.toCreateDto(s))
                    .orElseThrow(StudentNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(student);
    }
    //Revisar esto de nuevo mañana
    /*@GetMapping("/studentscourse/{id}")
    public ResponseEntity<StudentCourseDto> getStudentById2(@PathVariable Long id) {
        Optional<Student> student = studentService.find(id);
        if (student != null) {
            StudentCreateDto studentCreateDto = studentMapper.toCreateDto(student);
            Set<Course> courses = student.get().getCourses();
            Set<CourseCreateDto> courseDtos = courses.stream().map(courseMapper::toCreateDto).collect(Collectors.toSet());
            StudentCourseDto studentCourseDto = studentMapper.toCourseDto(studentCreateDto, courseDtos);
            return new ResponseEntity<>(studentCourseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
    @PostMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentCreateDto> createStudent(@RequestBody StudentDto studentDto) {
		Student student = studentMapper.toEntity(studentDto);
        Student studentCreated = null;
        try {
            studentCreated = studentService.create(student);
        } catch (Exception e) {
            throw new DuplicateCodigoException();
        }
		StudentCreateDto studentCreatedDto = studentMapper.toCreateDto(studentCreated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(studentCreatedDto.getId())
                        .toUri();
        return ResponseEntity.created(location).body(studentCreatedDto);
    }
    /*@PostMapping("/studentscourses/id")
    public ResponseEntity<StudentCourseDto> addCoursesToStudent(@PathVariable Long id, @RequestBody StudentCourseDto studentCourseDto) {
        Optional<Student> student = studentService.find(id);
        if(student == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            Set<Course> courses = new HashSet<>();
            for (CourseCreateDto courseCreateDto : studentCourseDto.getCourses()) {
                Course course = courseMapper.toEntity(courseCreateDto);
                course.setStudents(student.get());
                courses.add(course);
            }
            student.get().setCourses(courses);
            StudentCreateDto studentcreationDto = studentMapper.toCreateDto(student);
            StudentCourseDto savedStudentCourseDto = studentMapper.toCourseDto(studentcreationDto, studentCourseDto.getCourses());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudentCourseDto);
        }
    }/* 
    /*@PostMapping("/students")
    public ResponseEntity<Student> create(@RequestBody Student student){
        Student studentcreated = studentService.create(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentcreated.getId()).toUri();
        return ResponseEntity.created(location).body(studentcreated);
    }
    */
    @PutMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentCreateDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            Student studentUpdated = studentMapper.toEntity(studentDto);
            Optional<Student> savedStudent = studentService.update(id, studentUpdated);
            StudentCreateDto studentCreateDto = studentMapper.toCreateDto(savedStudent);
            return ResponseEntity.ok(studentCreateDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /*@PutMapping("/students/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody Student student){
        return studentService.update(id, student)
               .map(studentUpdated -> ResponseEntity.ok().body(studentUpdated))
               .orElseGet(()->{
                     Student studentCreated = studentService.create(student);
                     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(studentCreated.getId()).toUri();
                     return ResponseEntity.created(location).body(studentCreated);
               });
        
    }
    */
    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    /* 
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */
}
