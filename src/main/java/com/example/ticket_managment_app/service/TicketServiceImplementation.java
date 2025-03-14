package com.example.ticket_managment_app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.ticket_managment_app.dto.TicketDto;
import com.example.ticket_managment_app.exceptions.TicketNotFoundException;
import com.example.ticket_managment_app.exceptions.InvalidStatusException;
import com.example.ticket_managment_app.model.Ticket;
import com.example.ticket_managment_app.repository.ITicketRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketServiceImplementation implements ITicketManagementService {

    private final ITicketRepo repo;
    private final ModelMapper modelMapper;

    @Override
    public TicketDto registerTicket(TicketDto ticketDto) {
        if (ticketDto == null)
            throw new TicketNotFoundException("Ticket cannot be Null");

        // Set the default values for the new ticket
        ticketDto.setStatus(Optional.ofNullable(ticketDto.getStatus()).orElse("OPEN"));
        ticketDto.setPriority(Optional.ofNullable(ticketDto.getPriority()).orElse("LOW"));

        // Save the ticket to Mongo Database
        var ticket = modelMapper.map(ticketDto, Ticket.class);

        // Set the createdAt field to current timestamp
        ticket.setCreatedAt(LocalDateTime.now());

        // Save the ticket to MongoDB
        var savedTicket = repo.save(ticket);
        // map entity back to DTO and return
        return modelMapper.map(savedTicket, TicketDto.class);
    }

    @Override
    public TicketDto findTicketById(String ticketId) {
        var ticket = repo.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with the ID::" + ticketId));
        return modelMapper.map(ticket, TicketDto.class);
    }

    @Override
    public List<TicketDto> findAllTickets() {
        return repo.findAll()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto) {
        return Optional.ofNullable(ticketDto)
                .map(dto -> {
                    // Convert TicketDto to Ticket entity
                    var ticket = modelMapper.map(dto, Ticket.class);
                    // Save the updated ticket to the repository
                    var updatedTicket = repo.save(ticket);
                    // Map the updated Ticket entity back to TicketDto and return
                    return modelMapper.map(updatedTicket, TicketDto.class);
                })
                .orElseThrow(() -> new TicketNotFoundException("Ticket cannot be Null"));
    }


    @Override
    public String updateTicketStatus(String ticketId, String status) {
        // Validate the Status Value
        var validStatues = List.of("OPEN", "IN_PROGRESS", "CLOSED");

        if (!validStatues.contains(status.toUpperCase()))
            throw new InvalidStatusException("Invalid Status value. Allowed status values are::" + validStatues);

        // Fetch the ticket by Id
        var optionalTicket = repo.findById(ticketId);

        if (optionalTicket.isEmpty())
            throw new TicketNotFoundException("Ticket with the ID::" + ticketId + " not found");

        // Update the ticket status
        var ticket = optionalTicket.get();
        ticket.setStatus(status.toUpperCase());
        repo.save(ticket); // Save the updated ticket to datbase

        return "Ticket Status updated successfully";

    }

    @Override
    public String deleteTicketById(String ticketId) {
        Ticket ticket = repo.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket Not found for the given Id::" + ticketId));
        repo.deleteById(ticket.getId());
        return "Ticket with the Id::" + ticketId + " deleted successfully";

    }

    @Override
    public String deleteAllTickets() {
        repo.deleteAll();
        return "Deleted all tickets successfully";
    }

    @Override
    public List<TicketDto> findTicketsByStatus(String status) {
        return repo.findByStatus(status.toUpperCase())
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByPriority(String priority) {
        return repo.findByPriority(priority.toUpperCase())
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByReporter(String reporter) {
        return repo.findByReporter(reporter)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsAssignedTo(String assignedTo) {
        return repo.findByAssignedTo(assignedTo)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsWithStatusAndPriority(String status, String priority) {

        return repo.findByStatusAndPriority(status, priority)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsCreatedBefore(LocalDateTime localDateTime) {
        return repo.findByCreatedAtBefore(localDateTime)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsCreatedAfter(LocalDateTime localDateTime) {
        return repo.findByCreatedAtAfter(localDateTime)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsAssignedToWithStatus(String assignedTo, String status) {
        return repo.findByAssignedToAndStatus(assignedTo, status)
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findHighPriorityOpenTicket() {
        return repo.findHighPriorityOpenTickets()
                .stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
    }

}
