package com.uam.mercaditouam.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Publicacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
        property = "cif")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Publicacion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
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

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Messaging> messagingList;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<MainComment> mainCommentList;

    @OneToMany(mappedBy = "publication", fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
}