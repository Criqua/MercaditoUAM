package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@Data
public class StudentDTO extends UserDTO{

    private String phoneNumber;

    private String personalDescription;

    private Set<Long> following;

    private Set<Long> followers;

    private List<Long> publicationList;

    private List<MessagingDTO> sentMessages;

    private List<MessagingDTO> receivedMessages;

    private List<TicketDTO> ticketList;

    private List<MainCommentDTO> mainCommentList;

    private List<PurchaseDTO> purchaseList;
}
