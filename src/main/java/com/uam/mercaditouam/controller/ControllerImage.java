package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.service.IServiceImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ControllerImage {
    @Autowired
    private IServiceImage serviceImage;

    @GetMapping("/all")
    public List<Image> getAll() {
        return serviceImage.getALl();
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = serviceImage.findByName(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image")MultipartFile file) {
        try {
            return serviceImage.uploadImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody ImageDTO imageDTO) {
        return serviceImage.updateImage(imageDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return serviceImage.deleteImage(id);
    }
}
