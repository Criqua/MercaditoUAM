package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Imagen")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Imagen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", referencedColumnName = "ID_Publicacion")
    private Publication publication;

    @Lob
    @Column(name = "Imagen", nullable = false, columnDefinition = "VARBINARY(MAX)")
    private byte[] imageData;
}