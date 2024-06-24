package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoPurchase extends JpaRepository<Purchase, Long> {
}
