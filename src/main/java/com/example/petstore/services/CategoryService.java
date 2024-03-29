package com.example.petstore.services;


import com.example.petstore.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategory();

    void add(Category category);

    Category edit(int id);

    void delete(int n);

    Category getCategoryById(int n);
}
