package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PurchaseDTO {
    private Long id;
    private LocalDateTime purchaseDate;

    private Long publicationId;
    private Long studentId;
}
