package com.uam.mercaditouam.dto;

import lombok.Data;

@Data
public class CashOnDeliveryDTO extends PaymentDTO{
    private Double offeredAmount;
    private Double returnedAmount;
}
