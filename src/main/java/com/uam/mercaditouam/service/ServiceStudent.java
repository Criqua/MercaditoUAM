package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void createStudent(StudentDTO studentDTO) {
        Student student = repoStudent.findById(studentDTO.getCIF()).orElse(null);
        if(student == null) {
            student = new Student();
            student.setCIF(studentDTO.getCIF());
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setEmail(studentDTO.getEmail());
            student.setProfilePhoto(studentDTO.getProfilePhoto());
        }
        List<Publication> publications = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        for(PublicationDTO p : studentDTO.getPublicationList()) {
            Publication publication = repoPublication.findById(p.getId()).orElse(null);
            if(publication == null) {
                publication = new Publication();
                publication.setId(p.getId());
            }
            publication.setStudent(student);
            publications.add(publication);
        }
    }


    @Override
    public void deleteStudent(Long CIF) {
        repoStudent.deleteById(CIF);
    }
}
