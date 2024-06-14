package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Comentario")
@EqualsAndHashCode(callSuper = true)
@Data
public class MainComment extends Comment{
    @Column(name = "Calificacion_Otorgada")
    private Integer scoredRating;

    @OneToMany(mappedBy = "parentComment", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentResponses> answers;
}