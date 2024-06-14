package com.uam.mercaditouam.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class CommentDTO {
    private Long id;
    private Integer scoredRating;
    private String textBody;
    private LocalDateTime publishedDate;
    private List<CommentDTO> answers;
    //private Student student;
    //private Publication publication;
}
