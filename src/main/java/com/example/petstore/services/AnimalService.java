package com.example.petstore.services;


import com.example.petstore.entities.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAll();

    void add(Animal animal);

    Animal edit(int id);

    void delete(int n);

    Animal getAnimalById(int n);
}
