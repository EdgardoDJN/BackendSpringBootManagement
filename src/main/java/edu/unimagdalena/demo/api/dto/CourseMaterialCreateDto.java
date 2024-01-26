package edu.unimagdalena.demo.api.dto;

import edu.unimagdalena.demo.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialCreateDto {
    private Long id;
    private String url;
    private CourseCreateDto course;
}
