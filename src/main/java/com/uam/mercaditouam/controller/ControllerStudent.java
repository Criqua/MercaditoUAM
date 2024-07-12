package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.service.IServiceStudent;
import com.uam.mercaditouam.service.ServiceStudent;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class ControllerStudent {
    @Autowired
    private IServiceStudent serviceStudent;

    @GetMapping("/all")
    public List<Student> getAll() {
        return serviceStudent.getAll();
    }
    @GetMapping("/find/{CIF}")
    public ResponseEntity<Student> findById(@PathVariable("CIF") Long cif) {
        Student student = serviceStudent.findByCIF(cif);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody StudentDTO studentDTO) throws IOException {

        /*

        if(Long.toString(studentDTO.getCIF()).length() != 8) {
            return ResponseEntity.badRequest().body("El CIF no es valido.");
        }
        if(studentDTO.getName().length() > 15) {
            return ResponseEntity.badRequest().body("El nombre no es valido.");
        }
        if(studentDTO.getSurname().length() > 15) {
            return ResponseEntity.badRequest().body("El apellido no es valido.");
        }
        if(studentDTO.getPhoneNumber().length() > 12) {
            return ResponseEntity.badRequest().body("El numero no es valido.");
        }
        if (!studentDTO.getEmail().matches("^[A-Za-z0-9._%+-]+@uamv\\.edu\\.ni$")) {
            return ResponseEntity.badRequest().body("El correo no es valido.");
        }*/
        return serviceStudent.saveStudent(studentDTO);
    }

    @DeleteMapping("/delete/{CIF}")
    public ResponseEntity<String> delete(@PathVariable("CIF") Long cif) {
        return serviceStudent.deleteStudent(cif);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody StudentDTO studentDTO) {
        return  serviceStudent.updateStudent(studentDTO);
    }

    @PutMapping("/{idFollowing}/following/{idStudent}")
    public ResponseEntity<String> assignFollowingToStudent(
            @PathVariable("idFollowing") Long idFollowing,
            @PathVariable("idStudent") Long idFollower
            ) {
        return serviceStudent.assignFollowingToStudent(idFollowing, idFollower);
    }

    @PutMapping("/{idFollowing}/removeFollowing/{idStudent}")
    public ResponseEntity<String> removeFollowingFromStudent(
            @PathVariable("idFollowing") Long idFollowing,
            @PathVariable("idStudent") Long idFollower
    ) {
        return serviceStudent.removeFollowingFromStudent(idFollowing, idFollower);
    }

    @PostMapping("/login")
    public <T> T login(@RequestParam Long cif, @RequestParam String password) {
        var login = serviceStudent.login(cif, password);
        return (T) login;
    }

}

