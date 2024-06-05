package com.uam.mercaditouam.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdministratorDTO extends UserDTO{
    private List<TicketDTO> ticketListDTO;
}
