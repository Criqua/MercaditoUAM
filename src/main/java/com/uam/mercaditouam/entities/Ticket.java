package com.uam.mercaditouam.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Ticket")
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ticket")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Solicitante", referencedColumnName = "CIF")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Student requesterStudent;

    @Column(name = "Descripcion_Problema")
    private String problemDescription;

    @Column(name = "Fecha_Creacion")
    private LocalDateTime creationDate;

    @Column(name = "Estado_Ticket")
    private boolean ticketStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Administrador", referencedColumnName = "CIF")
    private Administrator administrator;
}