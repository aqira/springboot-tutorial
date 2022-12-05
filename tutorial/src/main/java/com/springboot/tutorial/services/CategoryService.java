package com.springboot.tutorial.services;

import com.springboot.tutorial.models.Category;
import com.springboot.tutorial.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("");
        });
    }

    public Category saveCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return findCategoryById(savedCategory.getId());
    }

    public boolean deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findById(id).isEmpty();
    }
}
