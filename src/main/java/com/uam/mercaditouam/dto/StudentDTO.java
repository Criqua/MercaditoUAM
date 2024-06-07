package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentDTO extends UserDTO{

    private String phoneNumber;

    private String personalDescription;

    private Set<StudentDTO> following;

    private Set<StudentDTO> followers;

    private List<PublicationDTO> publicationList;

    private List<MessagingDTO> sentMessages;

    private List<MessagingDTO> receivedMessages;

    private List<TicketDTO> ticketList;

    private List<CommentDTO> commentList;

    private List<PurchaseDTO> purchaseList;
}
