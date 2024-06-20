package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CommentResponsesDTO;
import com.uam.mercaditouam.dto.MainCommentDTO;
import com.uam.mercaditouam.entities.CommentResponses;
import com.uam.mercaditouam.entities.MainComment;
import com.uam.mercaditouam.repository.IRepoMainComment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceMainComment implements IServiceMainComment{
    @Autowired
    private IRepoMainComment repoMainComment;

    @Override
    public List<MainComment> getAll() {
        return repoMainComment.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        MainComment mainComment = repoMainComment.findById(id).orElse(null);
        if(mainComment == null) {
            return (T) ResponseEntity.badRequest().body("The comment does not exist.");
        }
        return (T) mainComment;
    }

    @Override
    public ResponseEntity<String> createMainComment(MainCommentDTO mainCommentDTO) {
        MainComment mainComment = repoMainComment.findById(mainCommentDTO.getId()).orElse(null);
        if(mainComment == null ) {
            mainComment = new MainComment();
            mainComment.setId(mainCommentDTO.getId());
            mainComment.setScoredRating(null);
            mainComment.setTextBody(mainCommentDTO.getTextBody());
            mainComment.setPublishedDate(mainCommentDTO.getPublishedDate());
            mainComment.setAnswers(null);
        } else if(repoMainComment.existsById(mainComment.getId())) {
            return ResponseEntity.badRequest().body("Comment already exists.");
        }
        return ResponseEntity.ok("Comment created;");
    }

    @Override
    public ResponseEntity<String> updateMainComment(MainCommentDTO mainCommentDTO) {
        MainComment mainComment = repoMainComment.findById(mainCommentDTO.getId()).orElse(null);
        if(mainComment == null) {
            return ResponseEntity.badRequest().body("Comment does not exist.");
        }
        mainComment.setScoredRating(mainCommentDTO.getScoredRating());
        mainComment.setTextBody(mainCommentDTO.getTextBody());
        mainComment.setPublishedDate(mainCommentDTO.getPublishedDate());
        mainComment.setAnswers(Optional.ofNullable(mainCommentDTO.getAnswers())
                .map(mainCommentDTOS -> mainCommentDTOS.stream()
                        .map(this::convertToCommentEntity)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList()));
        repoMainComment.save(mainComment);
        return ResponseEntity.ok("Comment updated.");
    }

    @Override
    public ResponseEntity<String> deleteMainComment(Long id) {
        MainComment mainComment = repoMainComment.findById(id).orElse(null);
        if(mainComment == null) {
            return ResponseEntity.badRequest().body("The comment does not exist.");
        }
        repoMainComment.deleteById(id);
        return ResponseEntity.ok("Comment deleted.");
    }

    private CommentResponses convertToCommentEntity(CommentResponsesDTO commentResponsesDTO) {
        CommentResponses commentResponses = new CommentResponses();
        commentResponses.setId(commentResponsesDTO.getId());
        commentResponses.setPublishedDate(commentResponsesDTO.getPublishedDate());
        commentResponses.setTextBody(commentResponsesDTO.getTextBody());
        return commentResponses;
    }
}
