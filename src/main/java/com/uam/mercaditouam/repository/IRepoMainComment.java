package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.MainComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoMainComment extends JpaRepository<MainComment, Long> {
}
