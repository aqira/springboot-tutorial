package com.springboot.tutorial.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.tutorial.models.Category;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryDto {

    private Integer id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    public CategoryDto() {
    }

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto(Integer id, String name, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
