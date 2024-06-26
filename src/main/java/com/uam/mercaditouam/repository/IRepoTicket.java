package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoTicket extends JpaRepository<Ticket, Long> {
}
