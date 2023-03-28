package edu.unimagdalena.demo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponse> teacherNotFoundHandler( TeacherNotFoundException ex, 
                            WebRequest wr){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setDescription(wr.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> StudentNotFoundHandler( StudentNotFoundException ex, 
                            WebRequest wr){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setDescription(wr.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> CourseNotFoundHandler( CourseNotFoundException ex, 
                            WebRequest wr){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setDescription(wr.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CourseMaterialNotFoundException.class)
    public ResponseEntity<ErrorResponse> CourseMaterialNotFoundHandler( CourseMaterialNotFoundException ex, 
                            WebRequest wr){
        ErrorResponse error = new ErrorResponse();
        error.setErrroCode(HttpStatus.NOT_FOUND.value());
        error.setLocalDateTime(LocalDateTime.now());
        error.setMessage(ex.getMessage());
        error.setDescription(wr.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
