package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.service.IServiceImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/find/{id}")
    public <T> T getById(@PathVariable("id") Long id) {
        return serviceImage.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ImageDTO imageDTO) {
        return serviceImage.createImage(imageDTO);
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
