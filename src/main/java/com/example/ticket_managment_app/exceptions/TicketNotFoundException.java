package com.example.ticket_managment_app.exceptions;

public class TicketNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public TicketNotFoundException(String message){
        super(message);
    }

}
