package me.lab.doggraphqlapi.service;

import me.lab.doggraphqlapi.entity.Dog;
import me.lab.doggraphqlapi.exception.BreedNotFoundException;
import me.lab.doggraphqlapi.exception.DogNotFoundException;
import me.lab.doggraphqlapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository dogRepository;
    @Override
    public List<Dog> getAllDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public List<String> findDogBreeds() {
        return dogRepository.getAllBreeds();
    }

    @Override
    public List<String> findDogOrigins() {
        return dogRepository.getAllOrigins();
    }

    @Override
    public List<String> findDogNames() throws Exception {
        return Optional.ofNullable(dogRepository.getAllNames())
                .orElseThrow(() -> new Exception("findDogNames func can't retrieve data."));
    }

    @Override
    public Dog findDogBreedById(Long id) {
        Optional<Dog> found = dogRepository.findById(id);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new DogNotFoundException("Not found dog Id !", id);
        }
    }



    @Override
    @Transactional
    public  Boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> dogs = dogRepository.findAll();
        for (Dog dog : dogs) {
            if (dog.getBreed().equals(breed)) {
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new BreedNotFoundException("Breed not found !", breed);
        }
        return deleted;
    }

    @Override
    @Transactional
    public Dog updateDogName(Long id, String newName) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()){
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Not found dog !", id);
        }
    }

}
