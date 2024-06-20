package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.*;
import com.uam.mercaditouam.entities.*;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ServiceStudent implements IServiceStudent  {

    @Autowired(required = true)
    private IRepoStudent repoStudent;


    @Override
    public List<Student> getAll() {
        return repoStudent.findAll();
    }

    @Override
    public <T> T findByCIF(Long cif) {
        Student student = repoStudent.findById(cif).orElse(null);
        if(student == null) {
            return (T) ResponseEntity.badRequest().body("El estudiante no existe.");
        }
        return (T) student;
    }

    @Override
    public ResponseEntity<String> saveStudent(StudentDTO studentDTO) {
        Student student = repoStudent.findById(studentDTO.getCIF()).orElse(null);
        if(student == null) {
            student = new Student();
            student.setCIF(studentDTO.getCIF());
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setEmail(studentDTO.getEmail());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setPersonalDescription(studentDTO.getPersonalDescription());
            student.setProfilePhoto(studentDTO.getProfilePhoto());
            student.setFollowing(new HashSet<>());
            student.setFollowers(new HashSet<>());
            student.setPublicationList(null);
            student.setSentMessages(null);
            student.setReceivedMessages(null);
            student.setTicketList(null);
            student.setMainCommentList(null);
            repoStudent.save(student);
        } else
        if(repoStudent.existsById(student.getCIF())) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        return ResponseEntity.ok("User created.");
    }

    @Override
    public ResponseEntity<String> updateStudent(StudentDTO studentDTO) {
        Student student = repoStudent.findById(studentDTO.getCIF()).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("User does not exist.");
        }
        student.setName(studentDTO.getName());
        student.setSurname(studentDTO.getSurname());
        student.setEmail(studentDTO.getEmail());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setPersonalDescription(studentDTO.getPersonalDescription());
        student.setProfilePhoto(studentDTO.getProfilePhoto());

        // Actualizar seguidores y seguidos si están presentes en el DTO
        student.setFollowing(Optional.ofNullable(studentDTO.getFollowing())
                .map(set -> set.stream()
                        .map(dto -> repoStudent.findById(dto.getCIF()).orElseThrow(() -> new RuntimeException("Student not found: " + dto.getCIF())))
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>()));

        student.setFollowers(Optional.ofNullable(studentDTO.getFollowers())
                .map(set -> set.stream()
                        .map(dto -> repoStudent.findById(dto.getCIF()).orElseThrow(() -> new RuntimeException("Student not found: " + dto.getCIF())))
                        .collect(Collectors.toSet()))
                .orElse(new HashSet<>()));

        student.setPublicationList(
                Optional.ofNullable(studentDTO.getPublicationList())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToPublicationEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );
        // Seguir mismo procedimiento para cada relacion OneToMany
        student.setSentMessages(Optional.ofNullable(studentDTO.getSentMessages())
                .map(messagingDTOS -> messagingDTOS.stream()
                        .map(this::convertToMessagingEntity).collect(Collectors.toList()))
                .orElse(Collections.emptyList())
        );

        student.setReceivedMessages(Optional.ofNullable(studentDTO.getReceivedMessages())
                .map(messagingDTOS -> messagingDTOS.stream()
                        .map(this::convertToMessagingEntity).collect(Collectors.toList()))
                .orElse(Collections.emptyList())
        );

        student.setTicketList(
                Optional.ofNullable(studentDTO.getTicketList())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToTicketEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );
        student.setMainCommentList(
                Optional.ofNullable(studentDTO.getMainCommentList())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToCommentEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );
        repoStudent.save(student);
        return ResponseEntity.ok("User updated");
    }

    @Override
    public ResponseEntity<String> deleteStudent(Long CIF) {
        Student student = repoStudent.findById(CIF).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("The cif does not exist.");
        }
        repoStudent.deleteById(CIF);
        return ResponseEntity.ok("User deleted.");
    }


    /**
     * Agregar al servicio publicacion, cada entidad deberá tener un método similar,
     * a fin de realizar mapeos correctos con diferentes relaciones entre entidades
     */
    private Publication convertToPublicationEntity(PublicationDTO publicationDTO) {
        Publication publication = new Publication();
        publication.setId(publicationDTO.getId());
        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setPrice(publicationDTO.getPrice());
        publication.setFeatured(publicationDTO.isFeatured());
        publication.setAvailability(publicationDTO.getAvailability());
        publication.setObservations(publicationDTO.getObservations());
        publication.setVisible(publicationDTO.isVisible());
        publication.setPurchaseList(
                Optional.ofNullable(publicationDTO.getPurchaseList())
                        .map(publicationDTOS -> publicationDTOS.stream()
                                .map(this::convertToPurchaseEntity)
                                .collect(Collectors.toList())
                        )
                        .orElse(Collections.emptyList())
        );
        return publication;
    }

    private Messaging convertToMessagingEntity(MessagingDTO messagingDTO) {
        Messaging messaging = new Messaging();
        messaging.setId(messagingDTO.getId());
        messaging.setContent(messagingDTO.getContent());
        messaging.setSubmittedDate(messagingDTO.getSubmittedDate());
        return messaging;
    }
    private Ticket convertToTicketEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setProblemDescription(ticketDTO.getProblemDescription());
        ticket.setCreationDate(ticketDTO.getCreationDate());
        ticket.setTicketStatus(ticketDTO.isTicketStatus());
        return ticket;
    }

    private MainComment convertToCommentEntity(MainCommentDTO commentDTO) {
        MainComment mainComment = new MainComment();
        mainComment.setId(commentDTO.getId());
        mainComment.setScoredRating(commentDTO.getScoredRating());
        mainComment.setTextBody(commentDTO.getTextBody());
        mainComment.setPublishedDate(commentDTO.getPublishedDate());

        //comment.setAnswers(commentDTO.getAnswers());
        BeanUtils.copyProperties(commentDTO.getAnswers(), mainComment.getAnswers());
        return mainComment;
    }

    private Purchase convertToPurchaseEntity(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDTO.getId());
        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        return purchase;
    }

}
