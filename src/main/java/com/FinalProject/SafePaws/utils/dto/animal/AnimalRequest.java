package com.FinalProject.SafePaws.utils.dto.animal;

import com.FinalProject.SafePaws.entity.Animal;
import com.FinalProject.SafePaws.entity.Shelter;
import com.FinalProject.SafePaws.utils.enums.AnimalStatus;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRequest {

    private String name;

    private String species;

    private String breed;

    private Integer weight;

    private Integer age;

    private AnimalStatus animalStatus;

    private String description;

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
