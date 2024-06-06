package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoPublication extends JpaRepository<Publication, Long> {
}
