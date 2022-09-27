package com.emse.spring.faircorp.service;

import com.emse.spring.faircorp.dto.ApiGouvAdressDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AddressSearchService {

    private final RestTemplate restTemplate;

    public AddressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://example.com").build();
    }


    public List<ApiGouvAdressDto> getListAddress(List<String> params) {
        String uriResponse = UriComponentsBuilder.fromUriString("https://api-adresse.data.gouv.fr/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        ApiGouvResponseDto respDto = restTemplate.getForObject(uriResponse, ApiGouvResponseDto.class);

        return respDto.;
    }

}