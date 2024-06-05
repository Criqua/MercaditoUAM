package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Publicacion")
@Data
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Publicacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Estudiante", referencedColumnName = "CIF")
    private Student student;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Image> imageList;

    @Column(name = "Titulo", nullable = false)
    private String title;

    @Column(name = "Descripcion", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Caregoria", referencedColumnName = "ID_Categoria")
    private Category category;

    @Column(name = "Precio", nullable = false)
    private Double price;

    @Column(name = "Esta_Destacada")
    private boolean isFeatured;

    @Column(name = "Disponibilidad")
    private AvailabilityType availability;

    @Column(name = "Observaciones_Ban_Adm", nullable = true)
    private String observations;

    @Column(name = "Esta_Visible")
    private boolean isVisible;

    private enum AvailabilityType {
        UNIQUE_ARTICLE, AVAILABLE, NOT_AVAILABLE
    }

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Messaging> messagingList;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Comment> commentSet;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
}