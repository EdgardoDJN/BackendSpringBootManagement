package edu.unimagdalena.demo.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("No se encontro el estudiante");
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
