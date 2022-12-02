package com.springboot.tutorial.services;

import com.springboot.tutorial.models.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategoryService {

    private static final HashMap<Integer, Category> categories = new HashMap<>();

    public List<Category> findAllCategory() {
        return new ArrayList<>(categories.values());
    }

    public Category findCategoryById(Integer id) {
        return categories.get(id);
    }

    public Category saveCategory(Category category) {
        boolean alreadyExist = categories.containsKey(category.getId());
        if (alreadyExist) {
            categories.replace(category.getId(), category);
        } else {
            categories.put(category.getId(), category);
        }
        return categories.get(category.getId());
    }

    public Category deleteCategoryById(Integer id) {
        return categories.remove(id);
    }
}
