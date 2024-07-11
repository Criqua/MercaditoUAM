package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensajeria")
@Data
public class Messaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Chat")
    private Long id;

    @Column(name = "ID_Publicacion")
    private Long publicationId;

    @Column(name = "CIF_Remitente")
    private Long senderStudent;

    @Column(name = "CIF_Destinatario")
    private Long recipientStudent;

    @Column(name = "Contenido")
    private String content;

    @Column(name = "Fecha_Enviado")
    private LocalDateTime submittedDate;
}