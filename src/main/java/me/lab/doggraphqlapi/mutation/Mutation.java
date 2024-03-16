package me.lab.doggraphqlapi.mutation;

import me.lab.doggraphqlapi.entity.Dog;
import me.lab.doggraphqlapi.exception.BreedNotFoundException;
import me.lab.doggraphqlapi.exception.DogNotFoundException;
import me.lab.doggraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
// NOT USED CLASS
@Component
public class Mutation /*implements GraphQLMutationResolver */ {

    private DogRepository dogRepository;

    public Mutation (DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        for (Dog dog : allDogs) {
            if (dog.getBreed().equals(breed)) {
                dogRepository.delete(dog);
                deleted = true;
            }
        }
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found !", breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);

        if (optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Can't find this dog !", id);
        }
    }

}
