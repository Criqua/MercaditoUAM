package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.CashOnDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoCashOnDelivery extends JpaRepository<CashOnDelivery, Long> {
}
