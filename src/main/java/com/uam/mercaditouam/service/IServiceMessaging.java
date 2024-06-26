package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.MessagingDTO;
import com.uam.mercaditouam.entities.Messaging;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceMessaging {
    public List<Messaging> getAll();
    <T> T findById(Long id);
    ResponseEntity<String> createMessaging(MessagingDTO messagingDTO);
    ResponseEntity<String> updateMessaging(MessagingDTO messagingDTO);
    ResponseEntity<String> deleteMessaging(Long id);
}
