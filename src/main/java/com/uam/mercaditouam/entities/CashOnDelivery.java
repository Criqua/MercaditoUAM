package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Pago_ContraEntrega")
@PrimaryKeyJoinColumn(name = "ID_ContraEntrega")
@Data
public class CashOnDelivery extends Payment {
    @Column(name = "Efectivo_Ofrecido")
    private Double offeredAmount;

    @Column(name = "Efectivo_Devuelto")
    private Double returnedAmount;
}
