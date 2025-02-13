package com.example.ticket_managment_app.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ticket_managment_app.dto.TicketDto;

public interface ITicketManagementService {

    TicketDto registerTicket(TicketDto ticketDto);

    TicketDto findTicketById(String ticketId);

    List<TicketDto> findAllTickets();

    TicketDto updateTicket(TicketDto ticketDto);

    String updateTicketStatus(String ticketId, String status);

    String deleteTicketById(String ticketId);

    String deleteAllTickets();

    List<TicketDto> findTicketsByStatus(String status);

    List<TicketDto> findTicketsByPriority(String priority);

    List<TicketDto> findTicketsByReporter(String reporter);

    List<TicketDto> findTicketsAssignedTo(String assignedTo);

    List<TicketDto> findTicketsWithStatusAndPriority(String ticketId, String status, String priority);

    List<TicketDto> findTicketsCreatedBefore(LocalDateTime localDateTime);

    List<TicketDto> findTicketsCreatedAfter(LocalDateTime localDateTime);

    List<TicketDto> findTicketsAssignedToWithStatus(String assignedTo, String status);

    List<TicketDto> findHighPriorityOpenTicket();

}
