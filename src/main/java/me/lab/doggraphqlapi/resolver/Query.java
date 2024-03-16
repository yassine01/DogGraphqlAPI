package me.lab.doggraphqlapi.resolver;

import me.lab.doggraphqlapi.entity.Dog;
import me.lab.doggraphqlapi.exception.DogNotFoundException;
import me.lab.doggraphqlapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
// NOT USED CLASS

@Component
public class Query /*implements GraphQLQueryResolver*/ {

    private final DogRepository dogRepository;

    public Query (DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) throws Exception {
        Optional<Dog> found = dogRepository.findById(id);
        if (found.isPresent()){
            return found.get();
        } else {
            throw new DogNotFoundException("Not found dog Id !", id);
        }
    }


}
