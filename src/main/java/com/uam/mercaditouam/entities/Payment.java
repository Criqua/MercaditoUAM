package com.uam.mercaditouam.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pago")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pago")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Compra", referencedColumnName = "ID_Compra")
    private Purchase purchase;
}