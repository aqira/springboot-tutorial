package com.springboot.tutorial;

import com.springboot.tutorial.dtos.CategoryDto;
import com.springboot.tutorial.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }
}
