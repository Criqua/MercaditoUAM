package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Comentario")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Estudiante", referencedColumnName = "CIF")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", referencedColumnName = "ID_Publicacion")
    private Publication publication;

    @Column(name = "Calificacion_Otorgada")
    private Integer scoredRating;

    @Column(name = "Cuerpo_Texto")
    private String textBody;

    @Column(name = "Fecha_Publicacion")
    private LocalDateTime publishedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_comentario_Padre", nullable = true)
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Comment> answers;
}