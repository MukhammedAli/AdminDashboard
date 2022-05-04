package com.example.petstore.services;

import com.example.petstore.entities.Animal;
import com.example.petstore.entities.Category;
import com.example.petstore.repositories.AnimalRepository;
import com.example.petstore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService{

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    @Override
    public void add(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public Animal edit(int id) {
        Optional<Animal> optional = animalRepository.findById(id);
        Animal animal = null;
        if (optional.isPresent()) {
            animal = optional.get();
        } else {
            throw new RuntimeException("Product not found " + id);
        }

        return animal;

    }

    @Override
    public void delete(int n) {
        animalRepository.deleteById(n);
    }


    @Override
    public Animal getAnimalById(int n) {
        return animalRepository.getById(n);
    }
}
