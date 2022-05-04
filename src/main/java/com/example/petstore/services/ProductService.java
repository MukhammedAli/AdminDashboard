package com.example.petstore.services;

import com.example.petstore.entities.Product;
import com.example.petstore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

//    public List<Product> getAllProductsByCategoryId(int id) {
//        return productRepository.findAllByCategory_Id(id);
//    }

}


