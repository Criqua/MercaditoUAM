package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.*;
import com.uam.mercaditouam.entities.*;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ServiceStudent implements IServiceStudent  {

    @Autowired
    private IRepoStudent repoStudent;

    @Autowired
    private IRepoPublication repoPublication;

    @Override
    public List<Student> getAll() {
        return repoStudent.findAll();
    }

    @Override
    public <T> T findByCIF(Long cif) {
        var students = repoStudent.findAll();
        Student student = repoStudent.findById(cif).orElse(null);
        if(student == null) {
            return (T) ResponseEntity.badRequest().body("El estudiante no existe.");
        }
        int index = 0;
        for(Student s : students) {
            if(student.equals(s)) {
                index = students.indexOf(s);
            }
        }
        return (T) students.get(index);
        /*Student student = repoStudent.findById(cif).orElse(null);
        if(student == null) {
            return (T) ResponseEntity.badRequest().body("El estudiante no existe.");
        }
        return (T) student;*/
        //return (T) repoStudent.findByCIF(cif).orElseThrow(() -> new EntityNotFoundException("Student not found"));
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
            student.setPassword(studentDTO.getPassword());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setPersonalDescription(studentDTO.getPersonalDescription());
            student.setProfilePhoto(studentDTO.getProfilePhoto());
            student.setFollowing(new HashSet<>());
            student.setFollower(new HashSet<>());
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
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setPersonalDescription(studentDTO.getPersonalDescription());
        student.setProfilePhoto(studentDTO.getProfilePhoto());

        // Actualizar seguidores y seguidos si están presentes en el DTO
        student.setFollowing(studentDTO.getFollowing());
        student.setFollower(studentDTO.getFollowers());

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
            return ResponseEntity.badRequest().body("The user does not exist.");
        }
        repoStudent.deleteById(CIF);
        return ResponseEntity.ok("User deleted.");
    }

    @Override
    public ResponseEntity<String> assignFollowingToStudent(Long idFollowing, Long idFollower) {
        Student student = repoStudent.findById(idFollower).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("The user does not exist");
        }
        Student following = repoStudent.findById(idFollowing).orElse(null);
        if(following == null) {
            return ResponseEntity.badRequest().body("The user does not exist");
        }
        student.getFollowing().add(idFollowing);
        following.getFollower().add(idFollower);
        repoStudent.save(student);
        return ResponseEntity.ok("Added following users");
    }

    @Override
    public ResponseEntity<String> removeFollowingFromStudent(Long idFollowing, Long idFollower) {
        Student student = repoStudent.findByCIF(idFollower).orElse(null);
        if(student == null) {
            return ResponseEntity.badRequest().body("The user does not exist.");
        }
        Student following = repoStudent.findByCIF(idFollowing).orElse(null);
        if(following == null) {
            return ResponseEntity.badRequest().body("The user does not exist");
        }
        if(student.getFollowing().isEmpty()) {
            return ResponseEntity.badRequest().body("The user is not following anyone");
        }
        student.getFollowing().remove(idFollowing);
        following.getFollower().remove(idFollower);
        repoStudent.save(student);
        return ResponseEntity.ok("Removed following");
    }

    /**
     * Agregar al servicio publicacion, cada entidad deberá tener un método similar,
     * a fin de realizar mapeos correctos con diferentes relaciones entre entidades
     */
    private Publication convertToPublicationEntity(PublicationDTO publicationDTO) {
        Publication publication = repoPublication.findById(publicationDTO.getId()).orElse(null);
        publication.setId(publication.getId());
        publication.setTitle(publication.getTitle());
        publication.setDescription(publication.getDescription());
        publication.setPrice(publication.getPrice());
        publication.setFeatured(publication.isFeatured());
        publication.setAvailability(publication.getAvailability());
        publication.setObservations(publication.getObservations());
        publication.setVisible(publication.isVisible());
        publication.setPurchaseList(publication.getPurchaseList());
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
