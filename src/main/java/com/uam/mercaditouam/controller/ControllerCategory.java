package com.uam.mercaditouam.controller;

import com.uam.mercaditouam.entities.Category;
import com.uam.mercaditouam.service.IServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class ControllerCategory {
    @Autowired
    private IServiceCategory serviceCategory;

    @GetMapping("/all")
    public List<Category> getAll() {
        return serviceCategory.getAll();
    }

    @GetMapping("/find/{id}")
    public <T> T getByID(@PathVariable("id") Long id) {
        var category = serviceCategory.findById(id);
        return (T) category;
    }
}
