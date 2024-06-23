package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.CommentResponsesDTO;
import com.uam.mercaditouam.entities.CommentResponses;
import com.uam.mercaditouam.service.IServiceCommentResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentResponse")
@CrossOrigin("*")
public class ControllerCommentResponses {
    @Autowired
    private IServiceCommentResponses serviceCommentResponses;

    @GetMapping("/all")
    public List<CommentResponses> getAll() {
        return serviceCommentResponses.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        var commentResponse = serviceCommentResponses.findById(id);
        return (T) commentResponse;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CommentResponsesDTO commentResponsesDTO) {
        return serviceCommentResponses.createCommentResponse(commentResponsesDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody CommentResponsesDTO commentResponsesDTO) {
        return serviceCommentResponses.updateCommentResponse(commentResponsesDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return serviceCommentResponses.deleteCommentResponse(id);
    }
}
