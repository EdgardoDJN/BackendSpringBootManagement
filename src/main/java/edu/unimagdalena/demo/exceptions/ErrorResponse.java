package edu.unimagdalena.demo.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int errroCode;
    private LocalDateTime localDateTime;
    private String message;
    private String description;
}
