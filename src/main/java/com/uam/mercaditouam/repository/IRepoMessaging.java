package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Messaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoMessaging extends JpaRepository<Messaging, Long> {
}
