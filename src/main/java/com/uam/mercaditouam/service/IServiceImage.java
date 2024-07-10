package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface IServiceImage {
    public List<Image> getALl();
    <T> T findByName(String fileName);
    ResponseEntity<String> uploadImage(List<MultipartFile> file, Long publicationId, Long studentId) throws IOException;
    ResponseEntity<String> updateImage(ImageDTO imageDTO);
    ResponseEntity<String> deleteImage(Long id);
}
