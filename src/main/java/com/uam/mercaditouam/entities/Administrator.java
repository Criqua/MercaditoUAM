package com.uam.mercaditouam.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "Administrador")
@EqualsAndHashCode(callSuper = true)
@Data
public class Administrator extends User{

    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Ticket> ticketList;
}