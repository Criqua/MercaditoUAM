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

    private AvailabilityType availability;

    private String observations;

    private boolean isVisible;

    private List<MessagingDTO> messagingListDTO;

    private List<MainCommentDTO> mainCommentList;

    private List<PurchaseDTO> purchaseList;
}
