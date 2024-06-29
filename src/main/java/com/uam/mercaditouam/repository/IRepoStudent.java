package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepoStudent extends JpaRepository<Student, Long> {
    @EntityGraph(attributePaths = {"following"})
    Optional<Student> findByCIF(Long CIF);
}
