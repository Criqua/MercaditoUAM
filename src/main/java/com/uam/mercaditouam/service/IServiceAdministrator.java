package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.AdministratorDTO;
import com.uam.mercaditouam.entities.Administrator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceAdministrator {
    public List<Administrator> getAll();
    ResponseEntity<String> createAdministrator(AdministratorDTO administratorDTO);
    ResponseEntity<String> updateAdministrator(AdministratorDTO administratorDTO);
    ResponseEntity<String> deleteAdministrator(Long id);
}
