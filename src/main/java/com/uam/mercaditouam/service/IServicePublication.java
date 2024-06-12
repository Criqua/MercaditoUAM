package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.Publication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServicePublication {
    public List<Publication> getAll();
    public <T> T findById(Long id);
    ResponseEntity<String> createPublication(PublicationDTO publicationDTO);
    ResponseEntity<String> updatePublication(PublicationDTO publicationDTO);
    ResponseEntity<String> deletePublication(Long id);
}
