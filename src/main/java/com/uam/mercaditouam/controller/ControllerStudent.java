package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.entities.User;
import com.uam.mercaditouam.service.IServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ControllerStudent {
    @Autowired
    private IServiceUser serviceUser;

    @GetMapping("/all")
    public List<Student> getAll() {
        return serviceUser.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Student student) {
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
        serviceUser.createStudent(student);
        return ResponseEntity.ok("User created.");
    }
}
