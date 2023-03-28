package edu.unimagdalena.demo.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException() {
        super("No se encontro el curso");
    }

    public  CourseNotFoundException(String message) {
        super(message);
    }
    
    

}