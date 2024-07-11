package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.MessagingDTO;
import com.uam.mercaditouam.entities.Messaging;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoMessaging;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMessaging implements IServiceMessaging{
    @Autowired
    private IRepoMessaging repoMessaging;
    @Autowired
    private IRepoPublication repoPublication;
    @Autowired
    private IRepoStudent repoStudent;

    @Override
    public List<Messaging> getAll() {
        return repoMessaging.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Messaging messaging = repoMessaging.findById(id).orElse(null);
        if(messaging == null) {
            return (T) ResponseEntity.badRequest().body("The messaging does not  exist");
        }
        return (T) messaging;
    }

    @Override
    public ResponseEntity<String> createMessaging(MessagingDTO messagingDTO) {
        Messaging messaging = repoMessaging.findById(messagingDTO.getId()).orElse(null);
        if(messaging == null) {
            messaging = new Messaging();
            messaging.setId(messagingDTO.getId());
            messaging.setContent(messagingDTO.getContent());
            messaging.setSubmittedDate(messagingDTO.getSubmittedDate());
            Publication publication = repoPublication.findById(messagingDTO.getPublicationId()).orElse(null);
            if(publication == null) {
                return ResponseEntity.badRequest().body("The publication does not exist");
            }
            messaging.setPublicationId(messagingDTO.getPublicationId());
            Student senderStudent = repoStudent.findById(messagingDTO.getSenderStudent()).orElse(null);
            if(senderStudent == null) {
                return ResponseEntity.badRequest().body("The sender student does not exist");
            }
            messaging.setSenderStudent(messagingDTO.getSenderStudent());
            Student recipientStudent = repoStudent.findById(messagingDTO.getRecipientStudent()).orElse(null);
            if(recipientStudent == null) {
                return ResponseEntity.badRequest().body("The recipient student does not exist");
            }
            messaging.setRecipientStudent(messagingDTO.getRecipientStudent());
            repoMessaging.save(messaging);
        } else if(repoMessaging.existsById(messaging.getId())) {
            return ResponseEntity.badRequest().body("Messaging already exists");
        }
        return ResponseEntity.ok("Messaging created");

    }

    @Override
    public ResponseEntity<String> updateMessaging(MessagingDTO messagingDTO) {
        Messaging messaging = repoMessaging.findById(messagingDTO.getId()).orElse(null);
        if(messaging == null) {
            return ResponseEntity.badRequest().body("Messaging does not exist");
        }
        messaging.setContent(messagingDTO.getContent());
        repoMessaging.save(messaging);
        return ResponseEntity.ok("Messaging updated");
    }

    @Override
    public ResponseEntity<String> deleteMessaging(Long id) {
        Messaging messaging = repoMessaging.findById(id).orElse(null);
        if (messaging == null) {
            return ResponseEntity.badRequest().body("Messaging does not exist");
        }
        repoMessaging.deleteById(id);
        return ResponseEntity.ok("Messaging deleted");
    }
}
