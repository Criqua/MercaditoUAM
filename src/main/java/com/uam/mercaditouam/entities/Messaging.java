package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mensajeria")
@Getter
@Setter
public class Messaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Chat")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", referencedColumnName = "ID_Publicacion")
    private Publication publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Remitente", referencedColumnName = "CIF")
    private Student senderStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Destinatario", referencedColumnName = "CIF")
    private Student recipientStudent;

    @Column(name = "Contenido")
    private String content;

    @Column(name = "Fecha_Enviado")
    private LocalDateTime submittedDate;
}