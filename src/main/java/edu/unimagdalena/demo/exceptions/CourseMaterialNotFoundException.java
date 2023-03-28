package edu.unimagdalena.demo.exceptions;

public class CourseMaterialNotFoundException extends RuntimeException {

    public CourseMaterialNotFoundException() {
        super("No se encontro el profesor");
    }

    public CourseMaterialNotFoundException(String message) {
        super(message);
    }
    
    

}