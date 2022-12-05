package com.springboot.tutorial.controllers;

import com.springboot.tutorial.models.Category;
import com.springboot.tutorial.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
@ResponseBody
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return service.findAllCategory();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return service.findCategoryById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "insert")
    public Category insertCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @RequestMapping(method = RequestMethod.POST, value = "update/{id}")
    public Category updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        category.setId(id);
        return service.saveCategory(category);
    }

    @RequestMapping(method = RequestMethod.POST, value = "delete/{id}")
    public boolean deleteCategory(@PathVariable Integer id) {
        return service.deleteCategoryById(id);
    }
}
