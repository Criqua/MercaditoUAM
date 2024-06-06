package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.repository.IRepoPublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePublication implements IServicePublication{
    @Autowired
    private IRepoPublication repoPublication;
    @Override
    public List<Publication> getAll() {
        return repoPublication.findAll();
    }

    @Override
    public void createPublication(PublicationDTO publicationDTO) {

    }

    @Override
    public void deletePublication(Long id) {

    }
}
