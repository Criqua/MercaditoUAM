package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.AdministratorDTO;
import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.service.IServiceAdministrator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@CrossOrigin("*")
public class ControllerAdministrator {
    @Autowired
    private IServiceAdministrator serviceAdministrator;

    @GetMapping("/all")
    public List<Administrator> getAll() {
        return serviceAdministrator.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody AdministratorDTO administratorDTO) {
        return serviceAdministrator.createAdministrator(administratorDTO);
    }

    @DeleteMapping("/delete/{CIF}")
    public ResponseEntity<String> delete(@PathVariable("CIF")Long cif) {
        if(cif == null) {
            return ResponseEntity.badRequest().body("The cif does not exist.");
        }
        return serviceAdministrator.deleteAdministrator(cif);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody AdministratorDTO administratorDTO) {
        if(administratorDTO.getCIF() == null) {
            return ResponseEntity.badRequest().body("The cif does not exist.");
        }
        return serviceAdministrator.updateAdministrator(administratorDTO);
    }
}
