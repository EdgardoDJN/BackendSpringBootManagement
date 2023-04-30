package edu.unimagdalena.demo.api;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unimagdalena.demo.api.dto.CourseCreateDto;
import edu.unimagdalena.demo.api.dto.CourseDto;
import edu.unimagdalena.demo.api.dto.CourseMapper;
import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.services.CourseService;
import edu.unimagdalena.demo.exceptions.CourseNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
   
    private CourseService courseService;

    
    private CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }
    @GetMapping("/courses/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseCreateDto> getCourse(@PathVariable Long id) {
		CourseCreateDto data = courseService.find(id)
                    .map(x -> courseMapper.toCreateDto(x))
                    .orElseThrow(CourseNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(data);
    }

    @GetMapping("/courses")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<Course> data = courseService.findAll();
        if(data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            List<CourseDto> courseDto = data.stream().map(courseMapper::toDto).collect(Collectors.toList()) ;
            return new ResponseEntity<>(courseDto, HttpStatus.OK);
        }
    }

    //Error encontrado, toca referenciar los metodos que estan por debajo de course porque sino no los encuentra por id, entoces en courseDto tanto a student como teacher son del tipo createDto para que tengan sus id y no se pierdan
    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseDto> createCourse(@RequestBody Course course) {
        Course course4 = courseService.create(course);
        return new ResponseEntity<CourseDto>(courseMapper.toDto(course4), HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseCreateDto> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> course2 = courseService.find(id);
        if (course2.isPresent()) {
            Optional<Course> savedCourse = courseService.update(id, course);
            return ResponseEntity.ok(courseMapper.toCreateDto(savedCourse));

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseCreateDto> deleteCourse(@PathVariable Long id) {
        Optional<Course> course = courseService.find(id);
        if (course.isPresent()) {
            courseService.delete(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    /*@PutMapping("/{id}/courses/{teacherId}")
    public ResponseEntity<CourseCreateDto> assignTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
        Optional<Course> course = courseService.assignTeacher(id, teacher);
        if(course.isPresent()){
            return ResponseEntity.ok(courseMapper.toCreateDto(course));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    
    }
    */
    
    
    /*@PutMapping("/{id}/courses/{studentId}")
    public ResponseEntity<CourseCreateDto> addStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Course> course = courseService.addStudent(id, student);
        if(course.isPresent()){
            return ResponseEntity.ok(courseMapper.toCreateDto(course));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    
    }
    */
    


    
}
