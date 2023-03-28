package edu.unimagdalena.demo.api.dto;

import java.util.Optional;

import org.springframework.stereotype.Component;

import edu.unimagdalena.demo.entidades.CourseMaterial;

@Component
public class CourseMaterialMapper {
    public CourseMaterialDto toDto(CourseMaterial courseMaterial) {
        CourseMaterialDto dto = new CourseMaterialDto();
        dto.setUrl(courseMaterial.getUrl());
        dto.setCourse(courseMaterial.getCourse());
        return dto;
    }
    public CourseMaterialCreateDto toCreateDto(CourseMaterial courseMaterial) {
        CourseMaterialCreateDto dto = new CourseMaterialCreateDto();
        dto.setId(courseMaterial.getId());
        dto.setUrl(courseMaterial.getUrl());
        dto.setCourse(courseMaterial.getCourse());
        return dto;
    }
    public CourseMaterial toEntity(CourseMaterialDto dto) {
        CourseMaterial entity = new CourseMaterial();
        entity.setUrl(dto.getUrl());
        entity.setCourse(dto.getCourse());
        return entity;
    }
    public CourseMaterial toEntity(CourseMaterialCreateDto dto) {
        CourseMaterial entity = new CourseMaterial();
        entity.setUrl(dto.getUrl());
        entity.setCourse(dto.getCourse());
        return entity;
    }
    public CourseMaterialDto toDto(Optional<CourseMaterial> courseMaterial) {
        CourseMaterialDto dto = new CourseMaterialDto();
        dto.setUrl(courseMaterial.get().getUrl());
        dto.setCourse(courseMaterial.get().getCourse());
        return dto;
    }
    public CourseMaterialCreateDto toCreateDto(Optional<CourseMaterial> courseMaterial) {
        CourseMaterialCreateDto prueba = new CourseMaterialCreateDto();
        prueba.setId(courseMaterial.get().getId());
        prueba.setUrl(courseMaterial.get().getUrl());
        prueba.setCourse(courseMaterial.get().getCourse());
        return prueba;
    }
    
}
