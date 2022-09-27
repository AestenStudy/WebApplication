package com.emse.spring.faircorp.dto;

import java.util.List;

public class ApiGouvResponseDto {
    private String version;
    private List<ApiGouvFeatureDto> features;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<ApiGouvFeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<ApiGouvFeatureDto> features) {
        this.features = features;
    }
}