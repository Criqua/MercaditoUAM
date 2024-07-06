package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DataImagen")
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Imagen")
    private Long id;

    private String name;
    private String type;

    @Lob
    @Column(name = "Imagen", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] imageData;
}
