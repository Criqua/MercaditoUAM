package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessagingDTO {
    private Long id;
    private String content;
    private LocalDateTime submittedDate;
    private Long publicationId;
    private Long senderStudent;
    private Long recipientStudent;
}
