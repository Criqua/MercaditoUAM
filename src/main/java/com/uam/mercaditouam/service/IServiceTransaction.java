package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.TransactionDTO;
import com.uam.mercaditouam.entities.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceTransaction {
    public List<Transaction> getAll();
    <T> T findById(Long id);
    ResponseEntity<String> createTransaction(TransactionDTO transactionDTO);
    ResponseEntity<String> updateTransaction(TransactionDTO transactionDTO);
    ResponseEntity<String> deleteTransaction(Long id);
}
