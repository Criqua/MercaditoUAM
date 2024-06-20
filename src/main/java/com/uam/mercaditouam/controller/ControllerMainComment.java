package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.MainCommentDTO;
import com.uam.mercaditouam.entities.MainComment;
import com.uam.mercaditouam.service.IServiceMainComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mainComment")
@CrossOrigin("*")
public class ControllerMainComment {
    @Autowired
    private IServiceMainComment serviceMainComment;

    @GetMapping("/all")
    public List<MainComment> getAll() {
        return serviceMainComment.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T getById(@PathVariable("id") Long id) {
        var mainComment = serviceMainComment.findById(id);
        return (T) mainComment;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody MainCommentDTO mainCommentDTO) {
        return serviceMainComment.createMainComment(mainCommentDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody MainCommentDTO mainCommentDTO) {
        if(mainCommentDTO.getId() == null) {
            return ResponseEntity.badRequest().body("THe comment does not exist.");
        }
        return serviceMainComment.updateMainComment(mainCommentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("The comment does not exist.");
        }
        return serviceMainComment.deleteMainComment(id);
    }
}
