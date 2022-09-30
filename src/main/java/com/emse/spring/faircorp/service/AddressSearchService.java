package com.emse.spring.faircorp.service;

import com.emse.spring.faircorp.dto.ApiGouvAddressDto;
import com.emse.spring.faircorp.dto.ApiGouvFeatureDto;
import com.emse.spring.faircorp.dto.ApiGouvResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressSearchService {
    private static final String url = "https://api-adresse.data.gouv.fr";
    private final RestTemplate restTemplate;

    public AddressSearchService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri(url).build();
    }

    public List<ApiGouvAddressDto> findAddress(List<String> params) {
        String uriResponse = UriComponentsBuilder.fromUriString(url + "/search")
                .queryParam("q", params)
                .queryParam("limit", 15)
                .build()
                .toUriString();

        ApiGouvResponseDto respDto = restTemplate.getForObject(uriResponse, ApiGouvResponseDto.class);

        return respDto == null ? new ArrayList<>() : respDto.getFeatures().stream().map(ApiGouvFeatureDto::getProperties).collect(Collectors.toList());
    }
}