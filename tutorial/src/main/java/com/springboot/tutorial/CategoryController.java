package com.springboot.tutorial;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("categories")
public class CategoryController {
    @GetMapping
    public String index(Model model) {
        List<Category> categories = List.of(
                new Category(1, "Beverages", LocalDate.now()),
                new Category(2, "Condiments", LocalDate.now())
        );
        model.addAttribute("categories", categories);
        return "/category/category-index.html";
    }
}
