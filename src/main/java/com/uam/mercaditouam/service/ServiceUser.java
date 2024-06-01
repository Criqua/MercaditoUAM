package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Administrator;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.entities.User;
import com.uam.mercaditouam.repository.IRepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUser implements IServiceUser {
    @Autowired
    private IRepoUser repo;
    @Override
    public List<Student> getAll() {
        return repo.findAll();
    }

    @Override
    public void createStudent(Student student) {
        repo.save(student);
    }

    @Override
    public void deleteStudent(Long CIF) {
        repo.deleteById(CIF);
    }
}
