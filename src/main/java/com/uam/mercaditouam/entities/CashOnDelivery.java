package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pago_ContraEntrega")
@Getter
@Setter
public class CashOnDelivery extends Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PagoEfectivo")
    private Long id;

    @Column(name = "Efectivo_Ofrecido")
    private Double offeredAmount;

    @Column(name = "Efectivo_Devuelto")
    private Double returnedAmount;
}
