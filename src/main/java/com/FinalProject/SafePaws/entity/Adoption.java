package com.FinalProject.SafePaws.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.FinalProject.SafePaws.utils.enums.AdoptionStatus;

@Entity
@Table(name = "adoption")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adoption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "inspection_date")
    private LocalDate inspectionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "adoption_status")
    private AdoptionStatus adoptionStatus;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
}
