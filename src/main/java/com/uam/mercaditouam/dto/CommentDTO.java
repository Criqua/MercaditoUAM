package com.uam.mercaditouam.dto;

import com.uam.mercaditouam.entities.Comment;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class CommentDTO {
    private Long id;
    private Integer scoredRating;
    private String textBody;
    private LocalDateTime publishedDate;
    private Set<CommentDTO> answers;
}
