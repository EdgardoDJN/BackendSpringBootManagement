package edu.unimagdalena.demo.exceptions;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException() {
        super("No se encontro el profesor");
    }

    public TeacherNotFoundException(String message) {
        super(message);
    }
    
    

}