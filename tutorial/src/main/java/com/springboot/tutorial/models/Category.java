package com.springboot.tutorial.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "Category_SEQ", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Category_SEQ", allocationSize = 1)
    @Column(name = "CategoryID")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "CreationDate")
    private LocalDate creationDate;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
