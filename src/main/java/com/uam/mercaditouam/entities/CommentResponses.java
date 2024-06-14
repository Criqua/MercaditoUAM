package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "Respuesta_Comentario")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResponses extends Comment {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Comentario")
    private MainComment parentMainComment;
}
