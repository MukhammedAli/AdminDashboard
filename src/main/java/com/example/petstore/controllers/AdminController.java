package com.example.petstore.controllers;

import com.example.petstore.entities.Category;
import com.example.petstore.entities.Product;
import com.example.petstore.services.AnimalService;
import com.example.petstore.services.CategoryService;
import com.example.petstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/images/products/large-product";
    @Autowired
    CategoryService categoryService;
    @Autowired
    AnimalService animalService;
    @Autowired
    ProductService productService;
    @GetMapping("/administrator")
    public String adminHome() {
        return "adminHome";
    }
    @GetMapping("/administrator/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/administrator/categories/add")
    public String getCategoriesAdd(Model model) {
        model.addAttribute("category",new Category());
        return "categoriesAdd";
    }


    @PostMapping("/administrator/categories/add")
    public String postCategories(@ModelAttribute("category") Category category) {
        categoryService.add(category);
        return "redirect:/administrator/categories";
    }

    @GetMapping("/administrator/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/administrator/categories";
    }

    @GetMapping("/administrator/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model) {
        Category category = categoryService.edit(id);

        model.addAttribute("category", category);
        return "categoriesAdd";
    }

    @GetMapping("/administrator/products")
    public String products(Model model) {
        model.addAttribute("products",productService.getAll());
        return "products";
    }

    @GetMapping("/administrator/products/add")
    public String productAddGet(Model model) {
        model.addAttribute("products", new Product());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    @PostMapping("/administrator/products/add")
    public String productAddPost(@ModelAttribute("product") Product product,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Product pr = new Product();
        pr.setProduct_id(product.getProduct_id());
        pr.setProduct_name(product.getProduct_name());
        pr.setCategory(categoryService.getCategoryById(product.getCategory().getCategory_id()));
        pr.setProduct_price(product.getProduct_price());
        pr.setProduct_discount(product.getProduct_discount());
        pr.setProduct_description(product.getProduct_description());
        pr.setAnimal(animalService.getAnimalById(product.getAnimal().getAnimal_id()));
        String imageUUID;
        if(!file.isEmpty()) {
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        pr.setProduct_img(imageUUID);
        productService.addProduct(pr);

        return "redirect:/administrator/products";
    }

    @GetMapping("/administrator/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.removeProductById(id);
        return "redirect:/administrator/products";
    }

    @GetMapping("/administrator/product/update/{id}")
    public String updateProductGet(@PathVariable int id, Model model) {
//        Product product = productService.getProductById(id).get();
//        Product productnew = new Product();
//        productnew.setProduct_id(product.getProduct_id());
//        productnew.setProduct_name(product.getProduct_name());
//        productnew.setCategory(categoryService.getCategoryById(product.getCategory().getCategory_id()));
//        productnew.setProduct_price(product.getProduct_price());
//        productnew.setAnimal(product.getAnimal());
//        productnew.setProduct_description(product.getProduct_description());
//        productnew.setProduct_img(product.getProduct_img());

        Product product = productService.getProductById(id).get();
        Product pr = new Product();
        pr.setProduct_id(product.getProduct_id());
        pr.setProduct_name(product.getProduct_name());
        pr.setCategory(categoryService.getCategoryById(product.getCategory().getCategory_id()));
        pr.setProduct_price(product.getProduct_price());
        pr.setProduct_discount(product.getProduct_discount());
        pr.setProduct_description(product.getProduct_description());
        pr.setAnimal(animalService.getAnimalById(product.getAnimal().getAnimal_id()));
        pr.setProduct_img(product.getProduct_img());

//        model.addAttribute("categories", categoryService.getAllCategory());
//        model.addAttribute("products", pr);
        return "productsAdd";
    }

}




