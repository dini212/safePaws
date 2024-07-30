package com.FinalProject.SafePaws.service.impl;

import com.FinalProject.SafePaws.entity.City;
import com.FinalProject.SafePaws.entity.Province;
import com.FinalProject.SafePaws.repository.CityRepository;
import com.FinalProject.SafePaws.repository.ProvinceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityRestClientImpl {

    private final CityRepository cityRepository;

    private final ProvinceRepository provinceRepository;

    RestClient provinceRestClient = RestClient.builder()
            .baseUrl("https://alamat.thecloudalert.com/api/kabkota")
            .build();

    ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    public void fetch() throws JsonProcessingException {

        List<City> cities = new ArrayList<>();

        for (Integer i = 1; i <= 34; i++){
            String json = provinceRestClient.get()
                    .uri("/get/?d_provinsi_id={i}",i.toString())
                    .retrieve()
                    .body(new ParameterizedTypeReference<String>() {});

            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultNode = rootNode.path("result");

            List<City> inLoopCities = objectMapper.readValue(
                    resultNode.toString(),
                    new TypeReference<List<City>>() {}
            );

            Province province = provinceRepository.findById(i.toString()).orElse(null);
            for (City city : inLoopCities) {
                city.setProvince(province);
            }

            cities.addAll(inLoopCities);
        }

        cityRepository.saveAll(cities);
    }

}
