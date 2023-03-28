package edu.unimagdalena.demo.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import edu.unimagdalena.demo.api.dto.TeacherDto;
import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.entidades.Student;
import edu.unimagdalena.demo.entidades.Teacher;
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
    public ResponseEntity<CourseCreateDto> getCourse(@PathVariable Long id) {
		CourseCreateDto data = courseService.find(id)
                    .map(x -> courseMapper.toCreateDto(x))
                    .orElseThrow(CourseNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(data);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<Course> data = courseService.findAll();
        if(data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            List<CourseDto> courseDto = data.stream().map(courseMapper::toDto).collect(Collectors.toList()) ;
            return new ResponseEntity<>(courseDto, HttpStatus.OK);
        }
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseCreateDto> createCourse(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        Course course2 = courseService.create(course);
        return new ResponseEntity<CourseCreateDto>(courseMapper.toCreateDto(course2), HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseCreateDto> updateCourse(@PathVariable Long id, @RequestBody CourseDto courseDto) {
        Optional<Course> course = courseService.find(id);
        if (course.isPresent()) {
            Course course2 = courseMapper.toEntity(courseDto);
            Optional<Course> savedCourse = courseService.update(id, course2);
            return ResponseEntity.ok(courseMapper.toCreateDto(savedCourse));

        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/courses/{id}")
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
