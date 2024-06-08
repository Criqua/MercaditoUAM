package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IServiceStudent {

    public List<Student> getAll();

    ResponseEntity<String> saveStudent(StudentDTO studentDTO);
    ResponseEntity<String> updateStudent(StudentDTO studentDTO);

    ResponseEntity<String> deleteStudent(Long CIF);
}