package me.lab.doggraphqlapi.controller;

import me.lab.doggraphqlapi.entity.Dog;
import me.lab.doggraphqlapi.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class DogController {

    private DogService dogService;

    @Autowired
    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    @QueryMapping
    @GetMapping("/api/dogs")
    public List<Dog> getAllDogs(){
        return dogService.getAllDogs();
    }

    @GetMapping("/api/dog/{id}/breed")
    public Dog getDogBreedById(@PathVariable Long id){
        return dogService.findDogBreedById(id);
    }

    @QueryMapping
    public Dog findDogBreedById(@Argument("id") Long id){
        return dogService.findDogBreedById(id);
    }

    @QueryMapping
    @GetMapping("/api/dogs/names")
    public List<String> getDogNames() throws Exception {
        return dogService.findDogNames();
    }

    @QueryMapping
    @GetMapping("/api/dogs/breeds")
    public List<String> getDogBreeds() {
        return dogService.findDogBreeds();
    }

    @QueryMapping
    @GetMapping("/api/dogs/origins")
    public List<String> getDogOrigins() {
        return dogService.findDogOrigins();
    }

    @PostMapping("/api/dogs/delete/{breed}")
    public Boolean deleteBreed(@PathVariable String breed) throws Exception {
        dogService.deleteDogBreed(breed);
        return Optional.of(true)
                .orElseThrow(() -> new Exception("something goes wrong while deleting.."));
    }

    @MutationMapping
    public Boolean deleteDogBreed(@Argument("breed") String breed){
        return dogService.deleteDogBreed(breed);
    }

    @MutationMapping
    public Dog updateDogName(@Argument("id") Long id,@Argument("newName") String newName) {
        return dogService.updateDogName(id, newName);
    }

    @PostMapping("/api/dog/{id}/update/{newName}")
    public Boolean updateName(@PathVariable("id") Long id,@PathVariable("newName") String newName) {
        dogService.updateDogName(id, newName);
        return true;
    }



}
