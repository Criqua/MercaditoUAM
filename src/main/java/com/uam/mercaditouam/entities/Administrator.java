package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Table(name = "Administrador")
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class Administrator extends User{

    @OneToMany(mappedBy = "administrator", fetch = FetchType.LAZY)
    private List<Ticket> ticketList;
}