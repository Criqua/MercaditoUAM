package com.uam.mercaditouam.dto;

import lombok.Data;

@Data
public class CommentResponsesDTO extends CommentDTO{
    private Long id;
    private Long mainCommentId;
    private Long publicationId;
}
