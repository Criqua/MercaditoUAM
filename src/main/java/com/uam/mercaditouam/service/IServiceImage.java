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
    <T> T findByStudentId(Long studentId);
    <T> T findByImageId(Long imageId);
    ResponseEntity<String> uploadImage(List<MultipartFile> file, Long publicationId, Long studentId) throws IOException;
    ResponseEntity<String> deleteImage(Long id);

    <T> T getImagesIDs(Long id);
}
