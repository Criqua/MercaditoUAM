package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceStudent implements IServiceStudent {
    @Autowired(required = true)
    private IRepoStudent repo;
    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public void createStudent(StudentDTO studentDTO) {
        Student student = repo.findById(studentDTO.getCIF()).orElse(null);
        if(student == null) {
            student = new Student();
            student.setCIF(studentDTO.getCIF());
            student.setName(studentDTO.getName());
            student.setSurname(studentDTO.getSurname());
            student.setEmail(studentDTO.getEmail());
            student.setProfilePhoto(studentDTO.getProfilePhoto());
        }
        List<Publication> publications = new ArrayList<>();
        for(PublicationDTO p : studentDTO.getPublicationList()) {
            Publication publication = repo.findById(p.getId()).orElse(null);

        }
    }


    @Override
    public void deleteStudent(Long CIF) {
        repo.deleteById(CIF);
    }
}
