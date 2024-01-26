package edu.unimagdalena.demo.api;

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
import edu.unimagdalena.demo.api.dto.CourseMaterialCreateDto;
import edu.unimagdalena.demo.api.dto.CourseMaterialDto;
import edu.unimagdalena.demo.api.dto.CourseMaterialMapper;
import edu.unimagdalena.demo.entities.CourseMaterial;
import edu.unimagdalena.demo.services.CourseMaterialService;
import edu.unimagdalena.demo.exceptions.CourseMaterialNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CourseMaterialController {
    
    //Segun la estructura del codigo que hemos trabajado el controlador debe contener estos metodos, ya si queremos saber los cursos de courseMaterial debemos crear un metodo que nos permita hacer eso desde Repository, services y luego controller
    private CourseMaterialService courseMaterialService;
    
    
    private CourseMaterialMapper courseMaterialMapper;

    public CourseMaterialController(CourseMaterialService courseMaterialService,
            CourseMaterialMapper courseMaterialMapper) {
        this.courseMaterialService = courseMaterialService;
        this.courseMaterialMapper = courseMaterialMapper;
    }

    @GetMapping("/coursesMaterial/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseMaterialCreateDto> getCourse(@PathVariable Long id) {
		CourseMaterialCreateDto data = courseMaterialService.find(id)
                    .map(x -> courseMaterialMapper.toCreateDto(x))
                    .orElseThrow(CourseMaterialNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(data);
    }
    @GetMapping("/coursesMaterial")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CourseMaterialDto>> getAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialService.findAll();
        List<CourseMaterialDto> courseMaterialDto = courseMaterials.stream()
                .map(courseMaterialMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(courseMaterialDto, HttpStatus.OK);
    }
    @PostMapping("/coursesMaterial")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseMaterialCreateDto> createCourseMaterial(@RequestBody CourseMaterial courseMaterial) {
        courseMaterial = courseMaterialService.create(courseMaterial);
        return ResponseEntity.ok(courseMaterialMapper.toCreateDto(courseMaterial));
    }
    @PutMapping("/coursesMaterial/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseMaterialCreateDto> updateCourseMaterial(@PathVariable Long id, @RequestBody CourseMaterial courseMaterial) {
        Optional<CourseMaterial> student = courseMaterialService.find(id);
        if (student.isPresent()) {
            Optional<CourseMaterial> savedCourseMaterial = courseMaterialService.update(id, courseMaterial);
            CourseMaterialCreateDto courseMaterialCreateDto = courseMaterialMapper.toCreateDto(savedCourseMaterial);
            return ResponseEntity.ok(courseMaterialCreateDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }
    @DeleteMapping("/coursesMaterial/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCourseMaterial(@PathVariable Long id) {
        Optional<CourseMaterial> courseMaterial = courseMaterialService.find(id);
        if (courseMaterial.isPresent()) {
            courseMaterialService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
