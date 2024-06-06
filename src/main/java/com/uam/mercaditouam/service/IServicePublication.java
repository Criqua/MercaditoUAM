package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.Publication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServicePublication {
    public List<Publication> getAll();
    void createPublication(PublicationDTO publicationDTO);
    void deletePublication(Long id);
}
