package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Compra")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Compra")
    private Long id;

    @Column(name = "ID_Publicacion")
    private Long publicationId;

    @Column(name = "CIF_Estudiante")
    private Long studentId;

    @Column(name = "Fecha_Compra")
    private LocalDateTime purchaseDate;
}