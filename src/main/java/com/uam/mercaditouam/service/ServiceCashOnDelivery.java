package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CashOnDeliveryDTO;
import com.uam.mercaditouam.entities.CashOnDelivery;
import com.uam.mercaditouam.repository.IRepoCashOnDelivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCashOnDelivery implements IServiceCashOnDelivery{
    @Autowired
    private IRepoCashOnDelivery repoCashOnDelivery;

    @Override
    public List<CashOnDelivery> getAll() {
        return repoCashOnDelivery.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        CashOnDelivery cashOnDelivery = repoCashOnDelivery.findById(id).orElse(null);
        if(cashOnDelivery == cashOnDelivery) {
            return (T) ResponseEntity.badRequest().body("Does not exist");
        }
        return (T) cashOnDelivery;
    }

    @Override
    public ResponseEntity<String> createCashOnDelivery(CashOnDeliveryDTO cashOnDeliveryDTO) {
        CashOnDelivery cashOnDelivery = repoCashOnDelivery.findById(cashOnDeliveryDTO.getId()).orElse(null);
        if(cashOnDelivery == null) {
            cashOnDelivery = new CashOnDelivery();
            cashOnDelivery.setId(cashOnDeliveryDTO.getId());
            cashOnDelivery.setOfferedAmount(cashOnDeliveryDTO.getOfferedAmount());
            cashOnDelivery.setReturnedAmount(cashOnDeliveryDTO.getReturnedAmount());
            repoCashOnDelivery.save(cashOnDelivery);
        } else if(repoCashOnDelivery.existsById(cashOnDelivery.getId())) {
            return ResponseEntity.badRequest().body("Already exists");
        }
        return ResponseEntity.ok("Created");
    }

    @Override
    public ResponseEntity<String> updateCashOnDelivery(CashOnDeliveryDTO cashOnDeliveryDTO) {
        CashOnDelivery cashOnDelivery = repoCashOnDelivery.findById(cashOnDeliveryDTO.getId()).orElse(null);
        if(cashOnDelivery == null) {
            return ResponseEntity.badRequest().body("Does not exist");
        }
        cashOnDelivery.setOfferedAmount(cashOnDeliveryDTO.getOfferedAmount());
        cashOnDelivery.setReturnedAmount(cashOnDeliveryDTO.getReturnedAmount());
        repoCashOnDelivery.save(cashOnDelivery);
        return ResponseEntity.ok("Updated");
    }

    @Override
    public ResponseEntity<String> deleteCashOnDelivery(Long id) {
        CashOnDelivery cashOnDelivery = repoCashOnDelivery.findById(id).orElse(null);
        if(cashOnDelivery == null) {
          return ResponseEntity.badRequest().body("Does not exist");
        }
        repoCashOnDelivery.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
