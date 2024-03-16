package me.lab.doggraphqlapi.repository;

import me.lab.doggraphqlapi.entity.Dog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {


    @Query("select d.breed from Dog d")
    List<String> getAllBreeds();

    @Query("select d from Dog d where d.id = :id")
    Dog getBreedById(Long id);

    @Query("select d.name from Dog d")
    List<String> getAllNames();

    @Query("select d.origin from Dog d")
    List<String> getAllOrigins();

    @Modifying
    @Query("delete from Dog d where d.breed = :breed")
    void deleteDogByBreed(String breed);

    @Modifying
    @Query("update Dog d set d.name = :newName where d.id = :id")
    void updateDogByName(Long id, String newName);


}
