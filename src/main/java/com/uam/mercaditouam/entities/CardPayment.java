package com.uam.mercaditouam.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Pago_Tarjeta")
@Getter
@Setter
public class CardPayment extends Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PagoTarjeta")
    private Long id;

    @Column(name = "Numero_Tarjeta")
    private String cardNumber;

    @Column(name = "Fecha_Expiracion")
    private LocalDate expirationDate;

    @Column(name = "CCV")
    private String CCV;

    @Column(name = "Titular")
    private String cardHolder;
}