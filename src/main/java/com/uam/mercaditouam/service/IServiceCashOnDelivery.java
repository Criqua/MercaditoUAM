package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CashOnDeliveryDTO;
import com.uam.mercaditouam.entities.CashOnDelivery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceCashOnDelivery {
    public List<CashOnDelivery> getAll();
    <T> T findById(Long id);
    ResponseEntity<String> createCashOnDelivery(CashOnDeliveryDTO cashOnDeliveryDTO);
    ResponseEntity<String> updateCashOnDelivery(CashOnDeliveryDTO cashOnDeliveryDTO);
    ResponseEntity<String> deleteCashOnDelivery(Long id);
}
