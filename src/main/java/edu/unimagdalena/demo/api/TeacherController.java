package edu.unimagdalena.demo.api;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import edu.unimagdalena.demo.api.dto.CourseMapper;
import edu.unimagdalena.demo.api.dto.TeacherCourseDto;
import edu.unimagdalena.demo.api.dto.TeacherCreationDto;
import edu.unimagdalena.demo.api.dto.TeacherDto;
import edu.unimagdalena.demo.api.dto.TeacherMapper;
import edu.unimagdalena.demo.entidades.Teacher;
import edu.unimagdalena.demo.exceptions.DuplicateCodigoException;
import edu.unimagdalena.demo.exceptions.TeacherNotFoundException;
import edu.unimagdalena.demo.services.TeacherService;

@RestController
@RequestMapping("/api/v1")
public class TeacherController {
    //Segun la estructura del codigo que hemos trabajado, el controlador debe contener estos metodos, ya si queremos saber los cursos de un profesor debemos crear un metodo que nos permita hacer eso desde Repository, services y luego controller
    
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TeacherCreationDto> getTeacherById(@PathVariable Long id) {
		TeacherCreationDto teacher = teacherService.find(id)
                    .map(t -> teacherMapper.toTeacherCreationDto(t))
                    .orElseThrow(TeacherNotFoundException::new);

        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
		
	}
    @GetMapping("/teachercourses/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherCourseDto> getCoursesByTeacherId(@PathVariable Long id) {
        Teacher teacher = teacherService.find(id).orElseThrow(TeacherNotFoundException::new);
        TeacherCourseDto teacherCourseDto = teacherMapper.toTeacherCourseDto(teacher);
        return ResponseEntity.status(HttpStatus.FOUND).body(teacherCourseDto);
                            
        
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    /*@DeleteMapping("/teachers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
    */
    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteTeacher(@PathVariable Long id) {
		teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}


