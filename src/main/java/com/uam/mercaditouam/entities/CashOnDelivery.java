package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Pago_ContraEntrega")
@PrimaryKeyJoinColumn(name = "ID_ContraEntrega")
@Getter
@Setter
public class CashOnDelivery extends Payment {
    @Column(name = "Efectivo_Ofrecido")
    private Double offeredAmount;

    @Column(name = "Efectivo_Devuelto")
    private Double returnedAmount;
}
