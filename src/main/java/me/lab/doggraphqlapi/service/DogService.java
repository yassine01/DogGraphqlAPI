package me.lab.doggraphqlapi.service;

import me.lab.doggraphqlapi.entity.Dog;

import java.util.List;

public interface DogService {

    List<Dog> getAllDogs();

    List<String> findDogBreeds();
    List<String> findDogOrigins();
    List<String> findDogNames() throws Exception;

    Dog findDogBreedById(Long id);

    Boolean deleteDogBreed(String breed);
    Dog updateDogName(Long id, String newName);


}
