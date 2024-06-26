package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface IServiceStudent {

    public List<Student> getAll();

    <T> T findByCIF(Long cif);
    ResponseEntity<String> saveStudent(StudentDTO studentDTO);
    ResponseEntity<String> updateStudent(StudentDTO studentDTO);

    ResponseEntity<String> deleteStudent(Long CIF);

    ResponseEntity<String> assignFollowingToStudent(Long idFollowing, Long idFollower);
}