package edu.unimagdalena.demo.api.dto;

import java.time.LocalDate;

import edu.unimagdalena.demo.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String lastName;
    private String firstName;
    private String codigo;
    private LocalDate birthDate;
    private Gender gender;
}
