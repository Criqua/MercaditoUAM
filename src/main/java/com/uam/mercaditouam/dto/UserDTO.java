package com.uam.mercaditouam.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public abstract class UserDTO {
    private Long CIF;
    private String name;
    private String surname;
    private String email;
    private byte[] profilePhoto;
}
