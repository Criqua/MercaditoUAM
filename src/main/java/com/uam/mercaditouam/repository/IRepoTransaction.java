package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoTransaction extends JpaRepository<Transaction, Long> {
}
