package com.FinalProject.SafePaws.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Province {
    @Id
    private String id;

    @JsonProperty("text")
    private String province;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<City> cities;
}
