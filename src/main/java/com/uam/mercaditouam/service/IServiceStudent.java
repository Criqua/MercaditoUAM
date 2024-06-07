package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.StudentDTO;
import com.uam.mercaditouam.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IServiceStudent {

    public List<Student> getAll();

    void saveStudent(StudentDTO studentDTO);

    void deleteStudent(Long CIF);
}