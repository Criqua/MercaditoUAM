package com.uam.mercaditouam.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Publicacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
  //      property = "id")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Publicacion")
    private Long id;

    /*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CIF_Estudiante", referencedColumnName = "CIF")
    private Student student;*/

    @Column(name = "ID_Estudiante")
    private Long studentCIF;

    @OneToMany(mappedBy = "idPublication", fetch = FetchType.LAZY)
    private List<Image> imageList;

    @Column(name = "Titulo", nullable = false)
    private String title;

    @Column(name = "Descripcion", nullable = false)
    private String description;

    @Column(name = "ID_Categoria")
    private Long categoryId;

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

    @OneToMany(mappedBy = "publicationId", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Messaging> messagingList;

    @OneToMany(mappedBy = "publicationId", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    //@EqualsAndHashCode.Exclude
    //@ToString.Exclude
    private List<MainComment> mainCommentList;

    @OneToMany(mappedBy = "publicationId", fetch = FetchType.LAZY)
    private List<Purchase> purchaseList;
}