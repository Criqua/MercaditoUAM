package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.*;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceStudent implements IServiceStudent {

    @Autowired(required = true)
    private IRepoStudent repoStudent;

    @Autowired
    private IRepoPublication repoPublication;

    @Override
    public List<Student> getAll() {
        return repoStudent.findAll();
    }

    @Override
    public void saveStudent(StudentDTO studentDTO) {
        Student student = repoStudent.findById(studentDTO.getCIF()).orElse(null);

        /**
         * Si la condición se cumple, significa que se debe crear (registrar) un nuevo
         * estudiante; por tanto, las listas provenientes por relaciones @OneToMany se
         * inicializan a null y conjuntos @ManyToMany se inicializan a vacías. De no ser así,
         * se actualiza el estudiante según sus datos existentes y atributos comunes.
         */
        if (student == null) {
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
            student.setCommentList(null);
        } else {
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
        }

        repoStudent.save(student);
    }

    @Override
    public void deleteStudent(Long CIF) {
        repoStudent.deleteById(CIF);
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

        return publication;
    }
}
