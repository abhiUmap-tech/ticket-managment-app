package com.example.ticket_managment_app.advices;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.ticket_managment_app.error.ErrorDetails;
import com.example.ticket_managment_app.exceptions.InvalidStatusException;
import com.example.ticket_managment_app.exceptions.TicketNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleTicketNotFoundException(TicketNotFoundException ticektNotFoundException){

        var errorDetails = new ErrorDetails(ticektNotFoundException.getMessage(), 404, LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ErrorDetails> handleInvalidStatusException(InvalidStatusException invalidStatusException){
        
        var errorDetails = new ErrorDetails(invalidStatusException.getMessage(), 400, LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception){
        var errorDetails = new ErrorDetails(exception.getMessage(), 400, LocalDateTime.now());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
