package com.uam.mercaditouam.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
public abstract class User {
    @Id
    @Column(name = "CIF")
    @Pattern(regexp = "^[0-9]+$")
    private Long CIF;

    @Column(name = "Nombre")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String name;

    @Column(name = "Apellido")
    @Pattern(regexp = "^[A-Za-z]+$")
    private String surname;

    @Column(name = "Correo")
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Email(message = "Correo invalido")
    private String email;

    @Column(name = "Contrasenia")
    private String password;
}