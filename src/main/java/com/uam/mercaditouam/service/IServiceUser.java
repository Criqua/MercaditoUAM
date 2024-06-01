package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IServiceUser {
    public List<Student> getAll();
    void createStudent(Student user);
    void deleteStudent(Long CIF);
}
