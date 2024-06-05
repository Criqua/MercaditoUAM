package com.uam.mercaditouam.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO extends PaymentDTO{
    private String cardNumber;
    private LocalDate expirationDate;
    private String CCV;
    private String cardHolder;
}
