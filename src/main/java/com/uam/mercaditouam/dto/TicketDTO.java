package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.Administrator;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDTO {
    private Long id;
    private String problemDescription;
    private LocalDateTime creationDate;
    private boolean ticketStatus;
}
