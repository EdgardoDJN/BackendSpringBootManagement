package edu.unimagdalena.demo.api.dto;
import edu.unimagdalena.demo.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseMaterialDto {
    private String url;
    private CourseDto course;
}
