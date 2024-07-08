package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.repository.IRepoImage;
import com.uam.mercaditouam.uitl.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImage implements IServiceImage{
    @Autowired
    private IRepoImage repoImage;
    @Override
    public List<Image> getALl() {
        return repoImage.findAll();
    }

    @Override
    public <T> T findByName(String fileName) {
        Optional<Image> dbImageData = repoImage.findByName(fileName);
        byte[] image = ImageUtils.decompressImage(dbImageData.get().getImageData());
        if (image == null) {
            return (T) ResponseEntity.badRequest().body("The image was not found");
        }
        return (T) image;
    }

    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file) throws IOException {
        Image imageData = repoImage.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return ResponseEntity.ok("The file" + file.getOriginalFilename() + "was uploaded");
        }
        return null;
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
