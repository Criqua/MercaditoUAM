package com.uam.mercaditouam.service;

import com.uam.mercaditouam.entities.Category;
import com.uam.mercaditouam.repository.IRepoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategory implements IServiceCategory{
    @Autowired
    private IRepoCategory repoCategory;

    @Override
    public List<Category> getAll() {
        return repoCategory.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Category category = repoCategory.findById(id).orElse(null);
        if(category == null) {
            return (T) ResponseEntity.badRequest().body("The category does not exist.");
        }
        return (T) category;
    }
}
