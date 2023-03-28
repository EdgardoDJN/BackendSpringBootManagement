package edu.unimagdalena.demo.api;

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
import edu.unimagdalena.demo.api.dto.CourseMaterialCreateDto;
import edu.unimagdalena.demo.api.dto.CourseMaterialDto;
import edu.unimagdalena.demo.api.dto.CourseMaterialMapper;
import edu.unimagdalena.demo.entidades.CourseMaterial;
import edu.unimagdalena.demo.services.CourseMaterialService;
import edu.unimagdalena.demo.exceptions.CourseMaterialNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class CourseMaterialController {
    
    private CourseMaterialService courseMaterialService;
    
    
    private CourseMaterialMapper courseMaterialMapper;

    public CourseMaterialController(CourseMaterialService courseMaterialService,
            CourseMaterialMapper courseMaterialMapper) {
        this.courseMaterialService = courseMaterialService;
        this.courseMaterialMapper = courseMaterialMapper;
    }

    @GetMapping("/coursesMaterial/{id}")
    public ResponseEntity<CourseMaterialCreateDto> getCourse(@PathVariable Long id) {
		CourseMaterialCreateDto data = courseMaterialService.find(id)
                    .map(x -> courseMaterialMapper.toCreateDto(x))
                    .orElseThrow(CourseMaterialNotFoundException::new);
        return ResponseEntity.status(HttpStatus.FOUND).body(data);
    }
    @GetMapping("/coursesMaterial")
    public ResponseEntity<List<CourseMaterialDto>> getAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = courseMaterialService.findAll();
        List<CourseMaterialDto> courseMaterialDto = courseMaterials.stream()
                .map(courseMaterialMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(courseMaterialDto, HttpStatus.OK);
    }
    @PostMapping("/coursesMaterial")
    public ResponseEntity<CourseMaterialCreateDto> createCourseMaterial(@RequestBody CourseMaterialDto courseMaterialDto) {
        CourseMaterial courseMaterial = courseMaterialMapper.toEntity(courseMaterialDto);
        courseMaterial = courseMaterialService.create(courseMaterial);
        return ResponseEntity.ok(courseMaterialMapper.toCreateDto(courseMaterial));
    }
    @PutMapping("/coursesMaterial/{id}")
    public ResponseEntity<CourseMaterialCreateDto> updateCourseMaterial(@PathVariable Long id, @RequestBody CourseMaterialDto courseMaterialDto) {
        Optional<CourseMaterial> student = courseMaterialService.find(id);
        if (student.isPresent()) {
            CourseMaterial courseMaterialUpdated = courseMaterialMapper.toEntity(courseMaterialDto);
            Optional<CourseMaterial> savedCourseMaterial = courseMaterialService.update(id, courseMaterialUpdated);
            CourseMaterialCreateDto courseMaterialCreateDto = courseMaterialMapper.toCreateDto(savedCourseMaterial);
            return ResponseEntity.ok(courseMaterialCreateDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    
    }
    @DeleteMapping("/coursesMaterial/{id}")
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
