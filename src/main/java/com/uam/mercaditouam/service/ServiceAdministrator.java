package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.AdministratorDTO;
import com.uam.mercaditouam.dto.TicketDTO;
import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.entities.Ticket;
import com.uam.mercaditouam.repository.IRepoAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceAdministrator implements IServiceAdministrator {

    @Autowired
    private IRepoAdministrator repoAdministrator;
    @Override
    public List<Administrator> getAll() {
        return repoAdministrator.findAll();
    }

    @Override
    public ResponseEntity<String> createAdministrator(AdministratorDTO administratorDTO) {
        Administrator administrator = repoAdministrator.findById(administratorDTO.getCIF()).orElse(null);
        if(administrator == null) {
            administrator = new Administrator();
            administrator.setCIF(administratorDTO.getCIF());
            administrator.setName(administratorDTO.getName());
            administrator.setSurname(administratorDTO.getSurname());
            administrator.setEmail(administratorDTO.getEmail());
            administrator.setProfilePhoto(administratorDTO.getProfilePhoto());
            administrator.setTicketList(null);
        } else if(repoAdministrator.existsById(administrator.getCIF())) {
            return ResponseEntity.badRequest().body("Administrator already exists.");
        }
        repoAdministrator.save(administrator);
        return ResponseEntity.ok("Administrator created.");
    }

    @Override
    public ResponseEntity<String> updateAdministrator(AdministratorDTO administratorDTO) {
        Administrator administrator = repoAdministrator.findById(administratorDTO.getCIF()).orElse(null);
        administrator = new Administrator();
        administrator.setCIF(administratorDTO.getCIF());
        administrator.setName(administratorDTO.getName());
        administrator.setSurname(administratorDTO.getSurname());
        administrator.setEmail(administratorDTO.getEmail());
        administrator.setProfilePhoto(administratorDTO.getProfilePhoto());
        administrator.setTicketList(
                Optional.ofNullable(administratorDTO.getTicketListDTO())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToTicketEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );
        repoAdministrator.save(administrator);
        return ResponseEntity.ok("Administrator updated.");
    }

    @Override
    public ResponseEntity<String> deleteAdministrator(Long id) {
        Administrator administrator = repoAdministrator.findById(id).orElse(null);
        if(administrator == null) {
            return ResponseEntity.badRequest().body("The id does not exist.");
        }
        repoAdministrator.deleteById(id);
        return ResponseEntity.ok("Administrator deleted.");
    }

    private Ticket convertToTicketEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setProblemDescription(ticketDTO.getProblemDescription());
        ticket.setCreationDate(ticketDTO.getCreationDate());
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        return ticket;
    }
}
