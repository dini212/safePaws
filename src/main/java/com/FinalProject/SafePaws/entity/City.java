package com.FinalProject.SafePaws.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City {
    @Id
    private String id;

    @JsonProperty("text")
    private String city;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<AddressShelter> addressShelterList;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<AddressUser> addressUserList;
}
