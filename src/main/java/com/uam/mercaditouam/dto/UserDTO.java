package com.uam.mercaditouam.dto;

import lombok.Data;

@Data
public abstract class UserDTO {
    private Long CIF;
    private String name;
    private String surname;
    private String email;
    private String password;
}