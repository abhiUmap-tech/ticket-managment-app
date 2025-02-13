package com.example.ticket_managment_app.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    private String id; // MongoDB document ID

    private String title; // A brief summary or title of the ticket

    private String description; // Detailed description of the issue or request

    private String status; // Ticket status (e.g., "OPEN", "IN_PROGRESS", "CLOSED")

    private String priority; // Ticket priority (e.g., "LOW", "MEDIUM", "HIGH")

    private String reporter; // The user who created/reported the ticket

    private String assignedTo; // The user responsible for handling the ticket

    @CreatedDate
    private LocalDateTime createdAt;

}
