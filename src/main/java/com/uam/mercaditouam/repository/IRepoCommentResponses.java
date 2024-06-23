package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.CommentResponses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepoCommentResponses extends JpaRepository<CommentResponses, Long> {
}
