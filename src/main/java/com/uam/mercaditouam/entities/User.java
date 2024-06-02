package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class User {
    @Id
    @Column(name = "CIF")
    private Long CIF;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Apellido")
    private String surname;

    @Column(name = "Correo")
    @Email(message = "Correo invalido")
    private String email;

    @Column(name = "Foto_Perfil", nullable = true)
    private String profilePhoto;
}