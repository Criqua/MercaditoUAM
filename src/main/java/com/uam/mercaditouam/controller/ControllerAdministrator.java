package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.service.IServiceAdministrator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class ControllerAdministrator {
    @Autowired
    private IServiceAdministrator serviceAdministrator;

    @GetMapping("/all")
    public List<Administrator> getAll() {
        return serviceAdministrator.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody Administrator administrator) {
        serviceAdministrator.createAdministrator(administrator);
        return ResponseEntity.ok("Administrator created.");
    }
}
