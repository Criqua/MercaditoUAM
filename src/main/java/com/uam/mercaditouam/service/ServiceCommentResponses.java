package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CommentResponsesDTO;
import com.uam.mercaditouam.entities.CommentResponses;
import com.uam.mercaditouam.repository.IRepoCommentResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCommentResponses implements IServiceCommentResponses{
    @Autowired
    private IRepoCommentResponses repoCommentResponses;
    @Override
    public List<CommentResponses> getAll() {
        return repoCommentResponses.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        CommentResponses commentResponses = repoCommentResponses.findById(id).orElse(null);
        if(commentResponses == null) {
            return (T) ResponseEntity.badRequest().body("The comment does not exist.");
        }
        return (T) commentResponses;
    }

    @Override
    public ResponseEntity<String> createCommentResponse(CommentResponsesDTO commentResponsesDTO) {
        CommentResponses commentResponses = repoCommentResponses.findById(commentResponsesDTO.getId()).orElse(null);
        if(commentResponses == null) {
            commentResponses = new CommentResponses();
            commentResponses.setId(commentResponsesDTO.getId());
            commentResponses.setTextBody(commentResponsesDTO.getTextBody());
            commentResponses.setPublishedDate(commentResponsesDTO.getPublishedDate());
        } else if(repoCommentResponses.existsById(commentResponses.getId())) {
            return ResponseEntity.badRequest().body("Comment already exists.");
        }
        repoCommentResponses.save(commentResponses);
        return ResponseEntity.ok("Comment created");
    }

    @Override
    public ResponseEntity<String> updateCommentResponse(CommentResponsesDTO commentResponsesDTO) {
        CommentResponses commentResponses = repoCommentResponses.findById(commentResponsesDTO.getId()).orElse(null);
        if(commentResponses == null) {
            return ResponseEntity.badRequest().body("The comment does not exist.");
        }
        commentResponses.setTextBody(commentResponsesDTO.getTextBody());
        commentResponses.setPublishedDate(commentResponsesDTO.getPublishedDate());
        repoCommentResponses.save(commentResponses);
        return ResponseEntity.ok("Comment updated");
    }

    @Override
    public ResponseEntity<String> deleteCommentResponse(Long id) {
        CommentResponses commentResponses = repoCommentResponses.findById(id).orElse(null);
        if(commentResponses == null) {
            return ResponseEntity.badRequest().body("The comment does not exist");
        }
        repoCommentResponses.deleteById(id);
        return ResponseEntity.ok("Comment deleted");
    }
}
