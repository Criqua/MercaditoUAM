package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.TicketDTO;
import com.uam.mercaditouam.entities.Ticket;
import com.uam.mercaditouam.repository.IRepoTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTicket implements IServiceTicket{
    @Autowired
    private IRepoTicket repoTicket;

    @Override
    public List<Ticket> getAll() {
        return repoTicket.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Ticket ticket = repoTicket.findById(id).orElse(null);
        if(ticket == null) {
            return (T) ResponseEntity.badRequest().body("Ticket does not exist");
        }
        return (T) ticket;
    }

    @Override
    public ResponseEntity<String> createTicket(TicketDTO ticketDTO) {
        Ticket ticket = repoTicket.findById(ticketDTO.getId()).orElse(null);
        if(ticket == null) {
            ticket = new Ticket();
            ticket.setId(ticketDTO.getId());
            ticket.setProblemDescription(ticketDTO.getProblemDescription());
            ticket.setCreationDate(ticketDTO.getCreationDate());
            ticket.setTicketStatus(ticketDTO.isTicketStatus());
            repoTicket.save(ticket);
        } else if(repoTicket.existsById(ticket.getId())) {
            return ResponseEntity.badRequest().body("Ticket already exists");
        }
        return ResponseEntity.ok("Ticket created");
    }

    @Override
    public ResponseEntity<String> updateTicket(TicketDTO ticketDTO) {
        Ticket ticket = repoTicket.findById(ticketDTO.getId()).orElse(null);
        if(ticket == null) {
            return ResponseEntity.badRequest().body("Ticket does not exist");
        }
        ticket.setProblemDescription(ticketDTO.getProblemDescription());
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        repoTicket.save(ticket);
        return ResponseEntity.ok("Ticket updated");
    }

    @Override
    public ResponseEntity<String> deleteTicket(Long id) {
        Ticket ticket = repoTicket.findById(id).orElse(null);
        if (ticket == null) {
            return ResponseEntity.badRequest().body("Ticket does not exist");
        }
        repoTicket.deleteById(id);
        return ResponseEntity.ok("Ticket deleted");
    }
}
