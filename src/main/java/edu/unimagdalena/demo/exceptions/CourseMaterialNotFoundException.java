package edu.unimagdalena.demo.exceptions;

public class CourseMaterialNotFoundException extends RuntimeException {

    public CourseMaterialNotFoundException() {
        super("No se encontro el curso material");
    }

    public CourseMaterialNotFoundException(String message) {
        super(message);
    }
    
    

}