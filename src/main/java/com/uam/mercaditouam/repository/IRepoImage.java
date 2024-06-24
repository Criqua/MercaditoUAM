package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoImage extends JpaRepository<Image, Long> {
}
