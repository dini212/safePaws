package com.FinalProject.SafePaws.utils.dto.animal;

import com.FinalProject.SafePaws.entity.Animal;
import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.utils.enums.AnimalStatus;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private String id;

    private String shelter;

    private String name;

    private String species;

    private String breed;

    private Integer weight;

    private Integer age;

    private AnimalStatus animalStatus;

    private String description;


    public static AnimalResponse response(Animal animal, Shelter shelter) {
        return AnimalResponse.builder()
                    .id(animal.getId())
                    .shelter(shelter.getName())
                    .name(animal.getName())
                    .species(animal.getSpecies())
                    .breed(animal.getBreed())
                    .weight(animal.getWeight())
                    .age(animal.getAge())
                    .animalStatus(animal.getAnimalStatus())
                    .description(animal.getDescription())
                    .build();
    }
}
