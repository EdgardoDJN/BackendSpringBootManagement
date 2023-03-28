package edu.unimagdalena.demo.api.dto;

import edu.unimagdalena.demo.entidades.Course;
import lombok.Data;

@Data
public class CourseMaterialDto {
    private String url;
    private Course course;
}
