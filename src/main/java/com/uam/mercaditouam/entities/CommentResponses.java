package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Respuesta_Comentario")
@Data
public class CommentResponses extends Comment {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Comentario", nullable = true)
    private Comment parentComment;
}
