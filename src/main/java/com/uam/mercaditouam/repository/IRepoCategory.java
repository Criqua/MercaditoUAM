package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoCategory extends JpaRepository<Category, Long> {
}
