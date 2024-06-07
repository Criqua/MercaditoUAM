package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.*;
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
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            student.setPersonalDescription(studentDTO.getPersonalDescription());
        }
        List<PublicationDTO> publications = studentDTO.getPublicationList();
        student.setPublicationList(publications);
        List<MessagingDTO> sentMessages = studentDTO.getSentMessages();
        List<MessagingDTO> receivedMessages = studentDTO.getReceivedMessages();
        List<TicketDTO> ticketList = studentDTO.getTicketList();
        List<CommentDTO> commentList = studentDTO.getCommentList();
        List<PurchaseDTO> purchaseList = studentDTO.getPurchaseList();
        repoStudent.save(student);
    }

    @Override
    public void deleteStudent(Long CIF) {
        repoStudent.deleteById(CIF);
    }
}
