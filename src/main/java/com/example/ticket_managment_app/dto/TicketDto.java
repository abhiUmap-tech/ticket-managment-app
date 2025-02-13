package com.example.ticket_managment_app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private String title; // A brief summary or title of the ticket

    private String description; // Detailed description of the issue or request

    private String status; // Ticket status (e.g., "OPEN", "IN_PROGRESS", "CLOSED")

    private String priority; // Ticket priority (e.g., "LOW", "MEDIUM", "HIGH")

    private String reporter; // The user who created/reported the ticket

    private String assignedTo; // The user responsible for handling the ticket

    private LocalDateTime createdAt;

}
