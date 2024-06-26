package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.TransactionDTO;
import com.uam.mercaditouam.entities.Transaction;
import com.uam.mercaditouam.repository.IRepoTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTransaction implements IServiceTransaction{
    @Autowired
    private IRepoTransaction repoTransaction;

    @Override
    public List<Transaction> getAll() {
        return repoTransaction.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Transaction transaction = repoTransaction.findById(id).orElse(null);
        if(transaction == null) {
            return (T) ResponseEntity.badRequest().body("The transaction does not exist");
        }
        return (T) transaction;
    }

    @Override
    public ResponseEntity<String> createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = repoTransaction.findById(transactionDTO.getId()).orElse(null);
        if(transaction == null) {
            transaction = new Transaction();
            transaction.setId(transactionDTO.getId());
            transaction.setCardNumber(transactionDTO.getCardNumber());
            transaction.setExpirationDate(transactionDTO.getExpirationDate());
            transaction.setCCV(transactionDTO.getCCV());
            transaction.setCardHolder(transactionDTO.getCardHolder());
            repoTransaction.save(transaction);
        } else
            if(repoTransaction.existsById(transaction.getId())) {
                return ResponseEntity.badRequest().body("Transaction already exists");
            }
            return ResponseEntity.ok("Transaction created");
    }

    @Override
    public ResponseEntity<String> updateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = repoTransaction.findById(transactionDTO.getId()).orElse(null);
       if(transaction == null) {
            return ResponseEntity.badRequest().body("Transaction does not exist");
        }
        transaction.setCardNumber(transactionDTO.getCardNumber());
        transaction.setExpirationDate(transactionDTO.getExpirationDate());
        transaction.setCCV(transactionDTO.getCCV());
        transaction.setCardHolder(transactionDTO.getCardHolder());
        repoTransaction.save(transaction);
        return ResponseEntity.ok("Transaction updated");
    }

    @Override
    public ResponseEntity<String> deleteTransaction(Long id) {
        Transaction transaction = repoTransaction.findById(id).orElse(null);
        if(transaction == null) {
            return ResponseEntity.badRequest().body("Transaction does not exist");
        }
        repoTransaction.deleteById(id);
        return ResponseEntity.ok("Transaction deleted");
    }
}
