package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.AdministratorDTO;
import com.uam.mercaditouam.entities.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceAdministrator {
    public List<Administrator> getAll();
    void createAdministrator(AdministratorDTO administratorDTO);
    void deleteAdministrator(Long id);
}
