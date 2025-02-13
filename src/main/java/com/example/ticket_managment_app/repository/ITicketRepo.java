package com.example.ticket_managment_app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.ticket_managment_app.model.Ticket;

public interface ITicketRepo extends MongoRepository<Ticket, String>{

    List<Ticket> findByStatus(String status);

    List<Ticket> findByPriority(String priority);

    List<Ticket> findByReporter(String reporter);

    List<Ticket> findByAssignedTo(String assignedTo);

    //Find all tickets with a specific Status and Priority
    List<Ticket> findByStatusAndPriority(String status, String priority);

    //Find all tickets created before a certain date
    List<Ticket> findByCreatedAtBefore(LocalDateTime localDateTime);

    //Find all tickets created after a certain date
    List<Ticket> findByCreatedAtAfter(LocalDateTime localDateTime);

    //Find all tickets with a title containing a specific keyword(case insensitive)
    List<Ticket> findByTitleIgnoreCase(String keyword);

    //Find all tickets assigned to a User with a specific Status
    List<Ticket> findByAssignedToAndStatus(String assignedTo, String status);

    //Custom Query : Find high-priority open tickets
    @Query("{'status' : 'OPEN', 'priority' : 'HIGH'}")
    List<Ticket> findHighPriorityOpenTickets();





}
