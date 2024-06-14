package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class Comment {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIF_Estudiante", referencedColumnName = "CIF")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Publicacion", referencedColumnName = "ID_Publicacion")
    private Publication publication;

    @Column(name = "Cuerpo_Texto")
    private String textBody;

    @Column(name = "Fecha_Publicacion")
    private LocalDateTime publishedDate;
}
