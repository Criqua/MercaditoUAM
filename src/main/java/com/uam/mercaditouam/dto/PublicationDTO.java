package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PublicationDTO {
    private Long id;
    private List<ImageDTO> imageList;
    private String title;
    private String description;
    private Double price;
    private boolean isFeatured;
    private PublicationDTO.AvailabilityType availability;
    private String observations;
    private boolean isVisible;
    private enum AvailabilityType {
        UNIQUE_ARTICLE, AVAILABLE, NOT_AVAILABLE
    }
    private List<MessagingDTO> messagingListDTO;
    private Set<CommentDTO> commentSet;
    private List<PurchaseDTO> purchaseList;
}