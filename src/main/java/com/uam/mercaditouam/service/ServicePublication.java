package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.CommentDTO;
import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.dto.MainCommentDTO;
import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.*;
import com.uam.mercaditouam.repository.IRepoCategory;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import com.uam.mercaditouam.uitl.ImageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ServicePublication implements IServicePublication{

    @Autowired
    private IRepoPublication repoPublication;

    @Autowired
    private IRepoStudent repoStudent;
    @Autowired
    private IRepoCategory repoCategory;

    @Override
    public List<Publication> getAll() {
        return repoPublication.findAll();
    }

    public <T> T findById(Long id) {
        Publication publication = repoPublication.findById(id).orElse(null);
        if(publication == null) {
            return (T) ResponseEntity.badRequest().body("The administrator does not exist.");
        }
        return (T) publication;
    }

    @Override
    public ResponseEntity<String> createPublication(PublicationDTO publicationDTO) {
        Publication publication = repoPublication.findById(publicationDTO.getId()).orElse(null);
        Student student = repoStudent.findById(publicationDTO.getStudentId()).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("The student does not exist");
        }
        if(publication == null) {
            publication = new Publication();
            publication.setId(publicationDTO.getId());
            /*publication.setImageList(Optional.ofNullable(publicationDTO.getImageList())
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
            publication.setMainCommentList(null);
            publication.setPurchaseList(null);
            publication.setStudentCIF(student.getCIF());
            Category category = repoCategory.findById(publicationDTO.getCategory().getId()).orElse(null);
            if (category == null) {
                return ResponseEntity.badRequest().body("The category does not exist");
            }
            publication.setCategory(category);
            //student.getPublicationList().add(publication);
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
            return ResponseEntity.badRequest().body("Publication does not exist.");
        }
        /*publication.setImageList(Optional.ofNullable(publicationDTO.getImageList())
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
        Category category = repoCategory.findById(publicationDTO.getCategory().getId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().body("The category does not exist");
        }
        publication.setCategory(category);
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

    /*@Override
    public ResponseEntity<String> assignStudentToPublication(Long idPublication, Long idStudent) {
        Publication publication = repoPublication.findById(idPublication).orElse(null);
        if(publication == null) {
            return ResponseEntity.badRequest().body("The publication does not exist");
        }
        Student student = repoStudent.findByCIF(idStudent).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("The user does not exist.");
        }
        //publication.setStudent(student);
        student.getPublicationList().add(publication);
        repoPublication.save(publication);
        return ResponseEntity.ok("Added user to publication");
    }*/

    private Image convertToImageEntity(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build();
        return image;
    }
    private MainComment convertToCommentEntity(MainCommentDTO commentDTO) {
        MainComment mainComment = new MainComment();
        mainComment.setId(commentDTO.getId());
        mainComment.setScoredRating(commentDTO.getScoredRating());
        mainComment.setTextBody(commentDTO.getTextBody());
        mainComment.setPublishedDate(commentDTO.getPublishedDate());
        if(commentDTO.getAnswers().isEmpty()) {
            commentDTO.getAnswers().clear();
            mainComment.getAnswers().clear();
            return mainComment;
        }
        BeanUtils.copyProperties(commentDTO.getAnswers(), mainComment.getAnswers());
        mainComment.setAnswers(mainComment.getAnswers());
        return mainComment;
    }
}
