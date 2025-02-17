package com.example.ticket_managment_app.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticket_managment_app.dto.TicketDto;
import com.example.ticket_managment_app.service.ITicketManagementService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final ITicketManagementService service;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new ticket",
            description = "Creates a new ticket based on the details provided")
    public ResponseEntity<TicketDto> registerTicket(@RequestBody TicketDto ticketDto) {
        var responseBody = service.registerTicket(ticketDto);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @GetMapping("/findTicketById/{ticketId}")
    @Operation(summary = "Get Ticket by ID",  
                          description = "Fetches a ticket details based on a Unique ID")
    public ResponseEntity<TicketDto> findTicketById(@PathVariable String ticketId) {
        var responseBody = service.findTicketById(ticketId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/findAllTickets")
    @Operation(summary = "Find all Tickets",
                            description = "Fetches all the ticket in the database")
    public ResponseEntity<List<TicketDto>> findAllTickets() {
        var responseBody = service.findAllTickets();
        return new ResponseEntity<>(responseBody, HttpStatus.FOUND);
    }

    @PutMapping("/updateTicket")
    @Operation(summary = "Updates the ticket details",  
                            description = "Updates the ticket details based on the data provided")
    public ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto) {
        var responseBody = service.updateTicket(ticketDto);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @PatchMapping("/updateTicketStatus/{ticketId}/{status}")
    @Operation(summary = "Update's ticket Status", 
                            description = "Updates the ticket's status")
    public ResponseEntity<String> updateTicketStatus(@PathVariable String ticketId, @PathVariable String status) {
        var responseBody = service.updateTicketStatus(ticketId, status);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTicketById/{ticketId}")
    @Operation(summary = "Delete the ticket by ID", 
                        description = "Delete's the ticket based on a Unique ID")
    public ResponseEntity<String> deleteTicketById(@PathVariable String ticketId) {
        var responseMessage = service.deleteTicketById(ticketId);
        return new ResponseEntity<>(responseMessage, HttpStatus.GONE);
    }

}
