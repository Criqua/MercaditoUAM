package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PurchaseDTO;
import com.uam.mercaditouam.entities.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServicePurchase {
    public List<Purchase> getALl();

    <T> T findById(Long id);
    ResponseEntity<String> createPurchase(PurchaseDTO purchaseDTO);
    ResponseEntity<String> updatePurchase(PurchaseDTO purchaseDTO);
    ResponseEntity<String> deletePurchase(Long id);
}
