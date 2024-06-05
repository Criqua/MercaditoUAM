package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Pago_Transaccion")
@PrimaryKeyJoinColumn(name = "ID_PagoTransaccion")
@Data
public class Transaction extends Payment {
    @Column(name = "Numero_Tarjeta")
    private String cardNumber;

    @Column(name = "Fecha_Expiracion")
    private LocalDate expirationDate;

    @Column(name = "CCV")
    private String CCV;

    @Column(name = "Titular")
    private String cardHolder;
}