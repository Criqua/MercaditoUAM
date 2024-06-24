package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CommentResponsesDTO;
import com.uam.mercaditouam.entities.CommentResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceCommentResponses {
    public List<CommentResponses> getAll();
    <T> T findById(Long id);
    ResponseEntity<String> createCommentResponse(CommentResponsesDTO commentResponsesDTO);
    ResponseEntity<String> updateCommentResponse(CommentResponsesDTO commentResponsesDTO);
    ResponseEntity<String> deleteCommentResponse(Long id);
}
