package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceCategory {
    public List<Category> getAll();
    <T> T findById(Long id);
}
