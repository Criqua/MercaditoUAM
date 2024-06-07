package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.service.IServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class ControllerStudent {
    @Autowired
    private IServiceStudent serviceStudent;

    @GetMapping("/all")
    public List<Student> getAll() {
        return serviceStudent.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody StudentDTO student) {
        if(Long.toString(student.getCIF()).length() != 8) {
            return ResponseEntity.badRequest().body("El CIF no es valido.");
        }
        if(student.getName().length() > 15) {
            return ResponseEntity.badRequest().body("El nombre no es valido.");
        }
        if(student.getSurname().length() > 15) {
            return ResponseEntity.badRequest().body("El apellido no es valido.");
        }
        if(student.getPhoneNumber().length() > 12) {
            return ResponseEntity.badRequest().body("El numero no es valido.");
        }
        serviceStudent.saveStudent(student);
        return ResponseEntity.ok("User created.");
    }
}
