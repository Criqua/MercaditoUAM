package com.uam.mercaditouam.dto;

import lombok.Data;

import java.util.List;

@Data
public class MainCommentDTO extends CommentDTO{
    private Long id;
    private Integer scoredRating;
    private List<CommentResponsesDTO> answers;
}
