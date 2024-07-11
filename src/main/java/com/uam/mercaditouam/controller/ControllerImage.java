package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.ImageDTO;
import com.uam.mercaditouam.entities.Image;
import com.uam.mercaditouam.service.IServiceImage;
import io.swagger.annotations.ApiParam;
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
@CrossOrigin("*")
public class ControllerImage {
    @Autowired
    private IServiceImage serviceImage;
    @GetMapping("/findStudentImage/{studentId}")
    public ResponseEntity<?> downloadStudentImage(@PathVariable("studentId") Long studentId){
        return serviceImage.findByStudentId(studentId);
    }

    @GetMapping("/findPublicationImage/{imageId}")
    public ResponseEntity<?> downloadPublicationImage(@PathVariable("imageId") Long imageId) {
        return serviceImage.findByImageId(imageId);
    }

    //@PostMapping("/{idPublication}/upload/{idStudent}")
    @RequestMapping(
            path = "/{idPublication}/upload/{idStudent}",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadImage(@RequestPart("file") @ApiParam(value = "File", required = true)
                                                  List<MultipartFile> file,
                                              @PathVariable("idPublication") Long idPublication,
                                              @PathVariable("idStudent") Long idStudent) {
        try {
            return serviceImage.uploadImage(file, idPublication, idStudent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return serviceImage.deleteImage(id);
    }

    @GetMapping("/getImagesIDs/{publicationId}")
    public <T> T getImagesIDs(@PathVariable("publicationId") Long id) {
        return serviceImage.getImagesIDs(id);
    }
}
