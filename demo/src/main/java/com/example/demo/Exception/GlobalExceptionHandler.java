package com.example.demo.Exception;

import com.example.demo.Dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //resourceNotFound handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handledResourceNotFoundException(ResourceNotFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); //returning object of ResponseEntity
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(//herer using ex refrence because it is having all the errors and forEaching all the errors becasue there may be many errors related to title desc content etc and then getting field name name default message and adding both thing in Map objecte and outside for each creatring proper responseEntity along with http status
                (error)->{
                    String field = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(field,message);
                }
        );
        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }

    //blogApiException handler
    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorDetails> handledblogApiException(BlogApiException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); //returning object of ResponseEntity
    }

    //this is the generic exception class , this will exceute for any exception if specific ahndler is not present
    //i.e title is unique if we create a new post using same title then this will execute
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handledblogAnyException(Exception e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR ); //returning object of ResponseEntity

    }

}
