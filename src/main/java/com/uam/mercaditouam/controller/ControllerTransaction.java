package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.TransactionDTO;
import com.uam.mercaditouam.entities.Transaction;
import com.uam.mercaditouam.service.IServiceTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class ControllerTransaction {
    @Autowired
    private IServiceTransaction serviceTransaction;

    @GetMapping("/all")
    public List<Transaction> getAll() {
        return serviceTransaction.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        var transaction = serviceTransaction.findById(id);
        return (T) transaction;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO) {
        return serviceTransaction.createTransaction(transactionDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTransaction(@RequestBody TransactionDTO transactionDTO) {
        return serviceTransaction.updateTransaction(transactionDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id) {
        return serviceTransaction.deleteTransaction(id);
    }
}
