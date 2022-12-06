package com.springboot.tutorial.converters;

import com.springboot.tutorial.dtos.CategoryDto;
import com.springboot.tutorial.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper mapper;

    public CategoryDto entityToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> entityToDto(List<Category> categories) {
        return categories.stream().map(category -> entityToDto(category)).collect(Collectors.toList());
    }

    public Category dtoToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }

    public List<Category> dtoToEntity(List<CategoryDto> categoryDtos) {
        return categoryDtos.stream().map(categoryDto -> dtoToEntity(categoryDto)).collect(Collectors.toList());
    }
}
