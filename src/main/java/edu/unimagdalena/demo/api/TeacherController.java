package edu.unimagdalena.demo.api;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.unimagdalena.demo.api.dto.CourseCreateDto;
import edu.unimagdalena.demo.api.dto.CourseMapper;
import edu.unimagdalena.demo.api.dto.TeacherCourseDto;
import edu.unimagdalena.demo.api.dto.TeacherCreationDto;
import edu.unimagdalena.demo.api.dto.TeacherDto;
import edu.unimagdalena.demo.api.dto.TeacherMapper;
import edu.unimagdalena.demo.entidades.Course;
import edu.unimagdalena.demo.entidades.Teacher;
import edu.unimagdalena.demo.exceptions.DuplicateCodigoException;
import edu.unimagdalena.demo.exceptions.TeacherNotFoundException;
import edu.unimagdalena.demo.services.TeacherService;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    //Falta terminar los restantes metodos
    //Inyectar esto para no estar instanciando
    
    private final TeacherService teacherService;
    
    private final TeacherMapper teacherMapper;
   
    private final CourseMapper courseMapper;

    
    
    
    public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper, CourseMapper courseMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
        this.courseMapper = courseMapper;
    }
    /*@GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> findAll(){
        List<Teacher> teachers = teacherService.findAll();
        return ResponseEntity.ok().body(teachers);
        
    }
    */
    @GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers() {
		List<Teacher> teachers = teacherService.findAll();
		List<TeacherDto> teacherDtos = teachers.stream().map(teacherMapper::toDto).collect(Collectors.toList());
		return new ResponseEntity<>(teacherDtos, HttpStatus.OK);
	}
    /*@GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> findById(@PathVariable("id") Long id){
        Optional<Teacher> teacher = teacherService.find(id);
        return ResponseEntity.of(teacher);
    }¨
    */
    @GetMapping("/teachers/{id}")
	public ResponseEntity<TeacherCreationDto> getTeacherById(@PathVariable Long id) {
		TeacherCreationDto teacher = teacherService.find(id)
                    .map(t -> teacherMapper.toTeacherCreationDto(t))
                    .orElseThrow(TeacherNotFoundException::new);

        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
		
	}
    @GetMapping("/teacherscourse/{id}")
    public ResponseEntity<TeacherCourseDto> getTeacherById2(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.find(id);
        if (teacher != null) {
            TeacherCreationDto teacherCreationDto = teacherMapper.toCreationDto(teacher);
            Set<Course> courses = teacher.get().getCourses();
            Set<CourseCreateDto> courseDtos = courses.stream().map(courseMapper::toCreateDto).collect(Collectors.toSet());
            TeacherCourseDto teacherCourseDto = teacherMapper.toCourseDto(teacherCreationDto, courseDtos);
            return new ResponseEntity<>(teacherCourseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /*@PostMapping("/teachers")
    public ResponseEntity<TeacherCreationDto> create(@RequestBody TeacherDto teacher)
    {
            Teacher newTeacher = teacherMapper.toEntity(teacher);
    
            Teacher teacherCreated = teacherService.create(newTeacher);
    
            TeacherCreationDto teacherCreationDto = teacherMapper.toCreationDto(teacherCreated);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(teacherCreationDto.getId())
                        .toUri();
            return ResponseEntity.created(location).body(teacherCreationDto);
    }
    */
    @PostMapping("/teachers")
	public ResponseEntity<TeacherCreationDto> createTeacher(@RequestBody TeacherDto teacherDto) {
		Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher teacherCreated = null;
        try {
            teacherCreated = teacherService.create(teacher);
        } catch (Exception e) {
            throw new DuplicateCodigoException();
        }
		TeacherCreationDto teacherCreationDto = teacherMapper.toTeacherCreationDto(teacherCreated);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(teacherCreationDto.getId())
                        .toUri();
        return ResponseEntity.created(location).body(teacherCreationDto);
    }
    @PostMapping("/teacherscourse")
    public ResponseEntity<TeacherCourseDto> addCourseToTeacher(@RequestBody TeacherCourseDto teacherCourseDto) {
        Optional<Teacher> teacher = teacherService.find(teacherCourseDto.getId());
        if(teacher == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
        {
            Set<Course> courses = new HashSet<>();
            for (CourseCreateDto courseCreateDto : teacherCourseDto.getCourses()) {
                Course course = courseMapper.toEntity(courseCreateDto);
                course.setTeacher(teacher.get());
                courses.add(course);
            }
            teacher.get().setCourses(courses);
            TeacherCreationDto teachercreationDto = teacherMapper.toCreationDto(teacher);
            TeacherCourseDto savedTeacherCourseDto = teacherMapper.toCourseDto(teachercreationDto, teacherCourseDto.getCourses());
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacherCourseDto);
        }
    }
	
    
    /*@PostMapping("/teachers")
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        Teacher teachercreated = teacherService.create(teacher);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teachercreated.getId()).toUri();
        return ResponseEntity.created(location).body(teachercreated);
    }
    */
    /*@PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> update(@PathVariable("id") Long id, @RequestBody Teacher teacher){
        return teacherService.update(id, teacher)
               .map(teacherUpdated -> ResponseEntity.ok().body(teacherUpdated))
               .orElseGet(()->{
                     Teacher teacherCreated = teacherService.create(teacher);
                     URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(teacherCreated.getId()).toUri();
                     return ResponseEntity.created(location).body(teacherCreated);
               });
        
    }
    */
    @PutMapping("/teachers/{id}")
	public ResponseEntity<TeacherCreationDto> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
		Optional<Teacher> teacherToUpdate = teacherService.find(id);
		if (teacherToUpdate.isPresent()) {
			Teacher updatedTeacher = teacherMapper.toEntity(teacherDto);
			Optional<Teacher> savedTeacher = teacherService.update(id, updatedTeacher);
			TeacherCreationDto teacherCreationDto = teacherMapper.toCreationDto(savedTeacher);
			return new ResponseEntity<>(teacherCreationDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
    @PutMapping("/teacherscourse/{id}")
    public ResponseEntity<TeacherCourseDto> updateTeacherCourse(@PathVariable Long id, @RequestBody TeacherCourseDto teacherCourseDto) {
        Optional<Teacher> teacherToUpdate = teacherService.find(id);
        if (teacherToUpdate.isPresent()) {
            Teacher updatedTeacher = teacherMapper.toEntity(teacherCourseDto);
            Optional<Teacher> savedTeacher = teacherService.update(id, updatedTeacher);
            TeacherCreationDto teacherCreationDto = teacherMapper.toCreationDto(savedTeacher);
            TeacherCourseDto teacherCourseDtoUpdated = teacherMapper.toCourseDto(teacherCreationDto, teacherCourseDto.getCourses());
            return new ResponseEntity<>(teacherCourseDtoUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /*@DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */
    @DeleteMapping("/teachers/{id}")
	public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id) {
		teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


