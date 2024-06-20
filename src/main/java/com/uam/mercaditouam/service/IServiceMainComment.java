package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.MainCommentDTO;
import com.uam.mercaditouam.entities.MainComment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceMainComment {
    public List<MainComment> getAll();
    <T> T findById(Long id);
    ResponseEntity<String> createMainComment(MainCommentDTO mainCommentDTO);
    ResponseEntity<String> updateMainComment(MainCommentDTO mainCommentDTO);
    ResponseEntity<String> deleteMainComment(Long id);
}
