package com.springboot.tutorial.controllers;

import com.springboot.tutorial.converters.CategoryConverter;
import com.springboot.tutorial.dtos.CategoryDto;
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

    private CategoryService service;
    private CategoryConverter converter;

    @Autowired
    public CategoryController(CategoryService service, CategoryConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return converter.entityToDto(service.findAllCategory());
    }

    @GetMapping("{id}")
    public CategoryDto getCategoryById(@PathVariable Integer id) {
        return converter.entityToDto(service.findCategoryById(id));
    }

    @PostMapping
    public CategoryDto insertCategory(@RequestBody CategoryDto dto) {
        Category category = converter.dtoToEntity(dto);
        return converter.entityToDto(service.saveCategory(category));
    }

    @PutMapping("{id}")
    public CategoryDto updateCategory(@PathVariable Integer id, @RequestBody CategoryDto dto) {
        Category category = converter.dtoToEntity(dto);
        return converter.entityToDto(service.updateCategory(id, category));
    }

    @DeleteMapping("delete/{id}")
    public boolean deleteCategory(@PathVariable Integer id) {
        return service.deleteCategoryById(id);
    }
}
