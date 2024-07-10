package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoImage;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoStudent;
import com.uam.mercaditouam.uitl.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceImage implements IServiceImage{
    @Autowired
    private IRepoImage repoImage;

    @Autowired
    private IRepoStudent repoStudent;

    @Autowired
    private IRepoPublication repoPublication;
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
    public ResponseEntity<String> uploadImage(List<MultipartFile> file, Long publicationId, Long studentId) throws IOException {
        List<Image> images = new ArrayList<>();
        if(publicationId == null && studentId == null) {
            return ResponseEntity.badRequest().body("At least one id must not be null");
        }
        Publication publication = repoPublication.findById(publicationId).orElse(null);
        Student student = repoStudent.findById(studentId).orElse(null);
        boolean studentOrNot = false;
        if(student != null) {
            studentOrNot = true;
        }
        int index = 0;
        for(MultipartFile i : file) {
            if (studentOrNot) {
                images.add(Image.builder()
                        .name(i.getOriginalFilename())
                        .type(i.getContentType())
                        .imageData(ImageUtils.compressImage(i.getBytes())).
                        student(student).build());
            } else {
                images.add(Image.builder()
                        .name(i.getOriginalFilename())
                        .type(i.getContentType())
                        .imageData(ImageUtils.compressImage(i.getBytes()))
                        .idPublication(publicationId).build());
            }
            /*images.add(Image.builder()
                    .name(i.getOriginalFilename())
                    .type(i.getContentType())
                    .imageData(ImageUtils.compressImage(i.getBytes())).idPublication(publicationId).build());
*/
            index++;
        }
        repoImage.saveAll(images);
        return ResponseEntity.badRequest().body("The file was not uploaded");
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
