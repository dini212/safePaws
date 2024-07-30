package com.FinalProject.SafePaws.utils.dto.animal;

import com.FinalProject.SafePaws.entity.Animal;
import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.utils.enums.AnimalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAnimalRequest {

    private String name;

    private String species;

    private String breed;

    private Integer weight;

    private Integer age;

    private AnimalStatus animalStatus;

    private String description;

    private Shelter shelter;

    public Animal convert(Shelter shelter) {
        return Animal.builder()
                .name(name)
                .species(species)
                .breed(breed)
                .weight(weight)
                .age(age)
                .animalStatus(animalStatus)
                .description(description)
                .shelter(shelter)
                .build();
    }

}
