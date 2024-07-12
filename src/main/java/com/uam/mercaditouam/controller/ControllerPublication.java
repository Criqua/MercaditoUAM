package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.PublicationDTO;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.service.IServicePublication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publication")
@CrossOrigin("*")
public class ControllerPublication {
    @Autowired
    private IServicePublication servicePublication;

    @GetMapping("/all")
    public List<Publication> getAll() {
        return servicePublication.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T getById(@PathVariable("id") Long id) {
        var publication = servicePublication.findById(id);
        return (T) publication;
    }

    @GetMapping("/getMostRecentPublications")
    public <T> T getRecentPublications() {
        return (T) servicePublication.getRecentPublications();
    }

    @GetMapping("/getRandomFeaturedPublications")
    public <T> T getRandomFeaturedPublications() {
        return (T) servicePublication.getRandomFeaturedPublications();
    }

    @GetMapping("/getRandomPublications")
    public <T> T getRandomPublications() {
        return (T) servicePublication.getRandomPublications();
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody PublicationDTO publicationDTO) {
        //comprobaciones

        return servicePublication.createPublication(publicationDTO);
    }
    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody PublicationDTO publicationDTO) {
        return servicePublication.updatePublication(publicationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        return servicePublication.deletePublication(id);
    }

    /*@PutMapping("/{idPublication}/students/{idStudent}")
    public ResponseEntity<String> assignStudentToPublication(
            @PathVariable("idPublication") Long idPublication,
            @PathVariable("idStudent") Long idStudent
    ) {
        return servicePublication.assignStudentToPublication(idPublication, idStudent);
    }*/
}
