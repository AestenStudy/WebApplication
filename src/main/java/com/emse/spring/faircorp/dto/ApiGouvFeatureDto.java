package com.emse.spring.faircorp.dto;

public class ApiGouvFeatureDto {
    private String type;
    private ApiGouvAdressDto properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ApiGouvAdressDto getProperties() {
        return properties;
    }

    public void setProperties(ApiGouvAdressDto properties) {
        this.properties = properties;
    }
}