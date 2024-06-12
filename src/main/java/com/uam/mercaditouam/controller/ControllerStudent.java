package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.service.IServiceStudent;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/student")
public class ControllerStudent {
    @Autowired
    private IServiceStudent serviceStudent;

    @GetMapping("/all")
    public List<Student> getAll() {
        return serviceStudent.getAll();
    }
    @GetMapping("/find/{CIF}")
    public <T> T findById(@PathVariable("CIF") Long cif) {
        var student = serviceStudent.findById(cif);
        return (T) student;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody StudentDTO studentDTO) {
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
        if(Objects.equals(studentDTO.getEmail(), "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
            return ResponseEntity.badRequest().body("El correo no es valido.");
        }
        return serviceStudent.saveStudent(studentDTO);
    }

    @DeleteMapping("/delete/{CIF}")
    public ResponseEntity<String> delete(@PathVariable("CIF") Long cif) {
        if(cif == null) {
            return ResponseEntity.badRequest().body("The cif does not exist.");
        }
        return serviceStudent.deleteStudent(cif);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody StudentDTO studentDTO) {
        if(studentDTO.getCIF() == null) {
            return ResponseEntity.badRequest().body("The cif does not exist.");
        }
        return  serviceStudent.updateStudent(studentDTO);
    }
}
