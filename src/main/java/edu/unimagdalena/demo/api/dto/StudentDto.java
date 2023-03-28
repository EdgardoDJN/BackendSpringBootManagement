package edu.unimagdalena.demo.api.dto;

import java.time.LocalDate;

import edu.unimagdalena.demo.entidades.Gender;
import lombok.Data;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private String codigo;
    private LocalDate birthDate;
    private Gender gender;
}
