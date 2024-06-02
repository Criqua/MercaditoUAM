package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IServiceStudent {
    public List<Student> getAll();
    void createStudent(Student user);
    void deleteStudent(Long CIF);
}
