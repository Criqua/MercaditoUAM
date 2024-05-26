package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    @Column(name = "CIF")
    private Long CIF;

    @Column(name = "Nombres")
    private String names;

    @Column(name = "Apellidos")
    private String surnames;

    @Column(name = "Correo")
    private String email;

    @Column(name = "Foto_Perfil", nullable = true)
    private String profilePhoto;
}