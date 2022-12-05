package com.springboot.tutorial.repositories;

import com.springboot.tutorial.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
