package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.repository.IRepoAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAdministrator implements IServiceAdministrator{
    @Autowired
    private IRepoAdministrator repoAdmin;

    @Override
    public List<Administrator> getAll() {
        return repoAdmin.findAll();
    }

    @Override
    public void createAdministrator(Administrator admin) {
        repoAdmin.save(admin);
    }

    @Override
    public void deleteAdministrator(Long id) {
        repoAdmin.deleteById(id);
    }
}
