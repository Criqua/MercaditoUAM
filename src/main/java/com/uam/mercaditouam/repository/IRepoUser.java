package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoUser extends JpaRepository<User, Long> {
}
