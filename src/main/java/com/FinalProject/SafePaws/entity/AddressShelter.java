package com.FinalProject.SafePaws.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address_shelter")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String description;
}
