package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceImage {
    public List<Image> getALl();
    <T> T findById(Long id);
    ResponseEntity<String> createImage(ImageDTO imageDTO);
    ResponseEntity<String> updateImage(ImageDTO imageDTO);
    ResponseEntity<String> deleteImage(Long id);
}
