package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Respuesta_Comentario")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResponses extends Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Comentario_Respuesta")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Comentario_Padre", referencedColumnName = "ID_Comentario_Padre")
    private MainComment parentMainComment;
}