package com.example.ticket_managment_app.exceptions;

public class InvalidStatusException extends RuntimeException{

    public InvalidStatusException(String message){
        super(message);
    }

}
