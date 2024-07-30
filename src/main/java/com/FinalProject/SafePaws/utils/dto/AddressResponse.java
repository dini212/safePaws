package com.FinalProject.SafePaws.utils.dto;

import lombok.*;

import com.FinalProject.SafePaws.entity.AddressShelter;
import com.FinalProject.SafePaws.entity.AddressUser;
import com.FinalProject.SafePaws.entity.City;
import com.FinalProject.SafePaws.entity.Province;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private String id;

    private String province;

    private String city;

    private String description;

    public static AddressResponse toResponse(AddressUser addressUser, City city, Province province) {

        return AddressResponse.builder()
                .id(addressUser.getId())
                .province(province.getProvince())
                .city(city.getCity())
                .description(addressUser.getDescription())
                .build();
    }

    public static AddressResponse toResponse(AddressShelter addressShelter, City city, Province province) {

        return AddressResponse.builder()
                .id(addressShelter.getId())
                .province(province.getProvince())
                .city(city.getCity())
                .description(addressShelter.getDescription())
                .build();
    }
}
