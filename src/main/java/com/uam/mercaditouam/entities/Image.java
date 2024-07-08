package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Imagen")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Imagen")
    private Long id;

    @Column(name = "Nombre_Imagen")
    private String name;

    @Column(name = "Tipo_Imagen")
    private String type;

    @Column(name = "ID_Publicacion")
    private Long idPublication;

    @OneToOne(mappedBy = "profileImage")
    private Student student;

    @Lob
    @Column(name = "Imagen", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] imageData;
}