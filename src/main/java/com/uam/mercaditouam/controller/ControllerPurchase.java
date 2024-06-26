package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.PurchaseDTO;
import com.uam.mercaditouam.entities.Purchase;
import com.uam.mercaditouam.service.IServicePurchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@CrossOrigin("*")
public class ControllerPurchase {
    @Autowired
    private IServicePurchase servicePurchase;

    @GetMapping("/all")
    public List<Purchase> getAll() {
        return servicePurchase.getALl();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        return servicePurchase.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PurchaseDTO purchaseDTO) {
        return servicePurchase.createPurchase(purchaseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody PurchaseDTO purchaseDTO) {
        return servicePurchase.updatePurchase(purchaseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return servicePurchase.deletePurchase(id);
    }
}
