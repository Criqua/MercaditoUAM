package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.repository.IRepoImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImage implements IServiceImage{
    @Autowired
    private IRepoImage repoImage;
    @Override
    public List<Image> getALl() {
        return repoImage.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Image image = repoImage.findById(id).orElse(null);
        if(image == null) {
            return (T) ResponseEntity.badRequest().body("The image does not exist.");
        }
        return (T) image;
    }

    @Override
    public ResponseEntity<String> createImage(ImageDTO imageDTO) {
        Image image = repoImage.findById(imageDTO.getId()).orElse(null);
        if(image == null) {
            image = new Image();
            image.setId(imageDTO.getId());
            image.setImageData(imageDTO.getImageData());
            repoImage.save(image);
        } else if(repoImage.existsById(image.getId())) {
            return ResponseEntity.badRequest().body("Image already exists");
        }
        return ResponseEntity.ok("Image created");
    }

    @Override
    public ResponseEntity<String> updateImage(ImageDTO imageDTO) {
        Image image = repoImage.findById(imageDTO.getId()).orElse(null);
        if(image == null) {
            return ResponseEntity.badRequest().body("The image does not exist");
        }
        image.setImageData(imageDTO.getImageData());
        repoImage.save(image);
        return ResponseEntity.ok("Image updated");
    }

    @Override
    public ResponseEntity<String> deleteImage(Long id) {
        Image image = repoImage.findById(id).orElse(null);
        if(image == null) {
            return ResponseEntity.badRequest().body("The image does not exist");
        }
        repoImage.deleteById(id);
        return ResponseEntity.ok("Image deleted");
    }
}
