package com.springboot.tutorial.services;

import com.springboot.tutorial.models.Category;
import com.springboot.tutorial.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            throw new IllegalArgumentException("Category with this ID cannot be found :" + id);
        });
    }

    public Category updateCategory(Integer id, Category category) {
        if (category.getCreationDate() == null) throw new IllegalArgumentException("Creation Date cannot be null!");
        Category existingCategory = findCategoryById(id);
        existingCategory.setCreationDate(category.getCreationDate());
        existingCategory.setName(category.getName());
        return saveCategory(existingCategory);
    }

    public Category saveCategory(Category category) {
        if (category.getCreationDate() == null) category.setCreationDate(LocalDate.now());
        Category savedCategory = categoryRepository.save(category);
        return findCategoryById(savedCategory.getId());
    }

    public boolean deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findById(id).isEmpty();
    }
}
