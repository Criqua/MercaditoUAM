package com.uam.mercaditouam.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class CommentDTO {
    private String textBody;
    private LocalDateTime publishedDate;
}
