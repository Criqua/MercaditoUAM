package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CommentDTO;
import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.Comment;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.repository.IRepoPublication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicePublication implements IServicePublication{
    @Autowired
    private IRepoPublication repoPublication;

    @Override
    public List<Publication> getAll() {
        return repoPublication.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Publication publication = repoPublication.findById(id).orElse(null);
        if (publication == null) {
            return (T) ResponseEntity.badRequest().body("The publicationn does not exist.");
        }
        return (T) publication;
    }

    @Override
    public ResponseEntity<String> createPublication(PublicationDTO publicationDTO) {
        Publication publication = repoPublication.findById(publicationDTO.getId()).orElse(null);
        if(publication == null) {
            publication = new Publication();
            publication.setId(publicationDTO.getId());
            publication.setImageList(Optional.ofNullable(publicationDTO.getImageList())
                    .map(imageDTOS -> imageDTOS.stream()
                            .map(this::convertToImageEntity)
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList()));
            /*if (publication.getImageList().isEmpty()) {
                return ResponseEntity.badRequest().body("Publications should at least have one image.");
            }*/
            publication.setTitle(publicationDTO.getTitle());
            publication.setDescription(publicationDTO.getDescription());
            publication.setPrice(publicationDTO.getPrice());
            publication.setFeatured(false);
            publication.setAvailability(publicationDTO.getAvailability());
            publication.setObservations(publicationDTO.getObservations());
            publication.setVisible(publicationDTO.isVisible());
            publication.setCommentList(null);
            publication.setPurchaseList(null);
            repoPublication.save(publication);
        } else if (repoPublication.existsById(publication.getId())) {
            return ResponseEntity.badRequest().body("Publication already exists.");
        }
        return ResponseEntity.ok("Publication created.");
    }

    @Override
    public ResponseEntity<String> updatePublication(PublicationDTO publicationDTO) {
        Publication publication = repoPublication.findById(publicationDTO.getId()).orElse(null);
        if(publication == null) {
            return ResponseEntity.badRequest().body("User does not exist.");
        }
        publication.setImageList(Optional.ofNullable(publicationDTO.getImageList())
                .map(imageDTOS -> imageDTOS.stream()
                        .map(this::convertToImageEntity)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList()));
        /*if (publication.getImageList().isEmpty()) {
            return ResponseEntity.badRequest().body("Publications should at least have one image.");
        }*/
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setPrice(publicationDTO.getPrice());
        publication.setFeatured(publicationDTO.isFeatured());
        publication.setAvailability(publicationDTO.getAvailability());
        publication.setObservations(publicationDTO.getObservations());
        publication.setVisible(publicationDTO.isVisible());
        publication.setCommentList(
                Optional.ofNullable(publicationDTO.getCommentList())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToCommentEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );

        repoPublication.save(publication);
        return ResponseEntity.ok("Publication updated.");
    }

    @Override
    public ResponseEntity<String> deletePublication(Long id) {
        Publication publication = repoPublication.findById(id).orElse(null);
        if(publication == null) {
            return ResponseEntity.badRequest().body("The id does not exist.");
        }
        repoPublication.deleteById(id);
        return ResponseEntity.ok("Publication deleted.");
    }

    private Image convertToImageEntity(ImageDTO imageDTO) {
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setImageData(imageDTO.getImageData());
        return image;
    }
    private Comment convertToCommentEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setScoredRating(commentDTO.getScoredRating());
        comment.setTextBody(commentDTO.getTextBody());
        comment.setPublishedDate(commentDTO.getPublishedDate());
        BeanUtils.copyProperties(commentDTO.getAnswers(), comment.getAnswers());
        return comment;
    }
}
