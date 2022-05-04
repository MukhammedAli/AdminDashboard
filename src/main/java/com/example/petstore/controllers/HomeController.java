package com.example.petstore.controllers;

import com.example.petstore.services.CategoryService;
import com.example.petstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAll());
        return "shopadmin";
    }

//    @GetMapping("/shop/category/{id}")
//    public String shop(Model model, @PathVariable int id) {
//        model.addAttribute("categories", categoryService.getAllCategory());
//        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
//        return "shopadmin";
//    }


}
