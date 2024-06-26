package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.MessagingDTO;
import com.uam.mercaditouam.entities.Messaging;
import com.uam.mercaditouam.service.IServiceMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messaging")
@CrossOrigin("*")
public class ControllerMessaging {
    @Autowired
    private IServiceMessaging serviceMessaging;

    @GetMapping("/all")
    public List<Messaging> getAll() {
        return serviceMessaging.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T findById(@PathVariable("id") Long id) {
        return serviceMessaging.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMessaging(@RequestBody MessagingDTO messagingDTO) {
        return serviceMessaging.createMessaging(messagingDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMessaging(@RequestBody MessagingDTO messagingDTO) {
        return serviceMessaging.updateMessaging(messagingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessaging(@PathVariable("id") Long id) {
        return serviceMessaging.deleteMessaging(id);
    }
}
