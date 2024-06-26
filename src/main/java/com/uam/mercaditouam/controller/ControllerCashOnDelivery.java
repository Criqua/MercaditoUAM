package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.CashOnDeliveryDTO;
import com.uam.mercaditouam.entities.CashOnDelivery;
import com.uam.mercaditouam.service.IServiceCashOnDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashOnDelivery")
@CrossOrigin("*")
public class ControllerCashOnDelivery {
    @Autowired
    private IServiceCashOnDelivery serviceCashOnDelivery;

    @GetMapping("/all")
    public List<CashOnDelivery> getAll() {
        return serviceCashOnDelivery.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        var cashOnDelivery = serviceCashOnDelivery.findById(id);
        return (T) cashOnDelivery;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCashOnDelivery(@RequestBody CashOnDeliveryDTO cashOnDeliveryDTO) {
        return serviceCashOnDelivery.createCashOnDelivery(cashOnDeliveryDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCashOnDelivery(@RequestBody CashOnDeliveryDTO cashOnDeliveryDTO) {
        return serviceCashOnDelivery.updateCashOnDelivery(cashOnDeliveryDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCashOnDelivery(@PathVariable("id") Long id) {
        return serviceCashOnDelivery.deleteCashOnDelivery(id);
    }
}
