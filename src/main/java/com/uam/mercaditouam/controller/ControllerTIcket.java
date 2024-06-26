package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.TicketDTO;
import com.uam.mercaditouam.entities.Ticket;
import com.uam.mercaditouam.service.IServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@CrossOrigin("*")
public class ControllerTIcket {
    @Autowired
    private IServiceTicket serviceTicket;

    @GetMapping("/all")
    public List<Ticket> getAll() {
        return serviceTicket.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        return serviceTicket.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTicket(@RequestBody TicketDTO ticketDTO) {
        return serviceTicket.createTicket(ticketDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTicket(@RequestBody TicketDTO ticketDTO) {
        return serviceTicket.updateTicket(ticketDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("id") Long id) {
        return serviceTicket.deleteTicket(id);
    }
}
