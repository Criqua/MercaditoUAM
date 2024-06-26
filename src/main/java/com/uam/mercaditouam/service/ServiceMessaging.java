package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.MessagingDTO;
import com.uam.mercaditouam.entities.Messaging;
import com.uam.mercaditouam.repository.IRepoMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMessaging implements IServiceMessaging{
    @Autowired
    private IRepoMessaging repoMessaging;

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
