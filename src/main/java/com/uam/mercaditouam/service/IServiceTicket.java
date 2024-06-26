package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.TicketDTO;
import com.uam.mercaditouam.entities.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceTicket {
    public List<Ticket> getAll();

    <T> T findById(Long id);
    ResponseEntity<String> createTicket(TicketDTO ticketDTO);
    ResponseEntity<String> updateTicket(TicketDTO ticketDTO);
    ResponseEntity<String> deleteTicket(Long id);
}
