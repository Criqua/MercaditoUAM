package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceAdministrator {
    public List<Administrator> getAll();
    void createAdministrator(Administrator admin);
    void deleteAdministrator(Long id);
}
