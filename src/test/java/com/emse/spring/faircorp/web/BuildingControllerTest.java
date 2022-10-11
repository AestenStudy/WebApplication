package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.api.BuildingController;
import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.model.Building;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = BuildingController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class BuildingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BuildingDao buildingDao;

    @Test
    void shouldLoadBuildings() throws Exception {
        given(buildingDao.findAll()).willReturn(List.of(
                createBuilding("B1"),
                createBuilding("B2")
        ));

        mockMvc.perform(get("/api/buildings").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("B1", "B2")));
    }

    @Test
    void shouldLoadABuildingAndReturnNullIfNotFound() throws Exception {
        given(buildingDao.findById(999L)).willReturn(new Building());

        mockMvc.perform(get("/api/buildings/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
    void shouldLoadABuilding() throws Exception {
        //use of any() instead of 999L otherwise error because equals() method isn't what we expect it to be
        given(buildingDao.findById(any())).willReturn(Optional.of(createBuilding("B1")));

        mockMvc.perform(get("/api/buildings/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("$.name").value("B1"));
    }

    @Test
    void shouldUpdateBuilding() throws Exception {
        Building expectedBuilding = createBuilding("B1");
        expectedBuilding.setId(1L);
        String json = objectMapper.writeValueAsString(new BuildingDto(expectedBuilding));

        given(buildingDao.getReferenceById(anyLong())).willReturn(expectedBuilding);

        mockMvc.perform(post("/api/buildings").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("B1"))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void shouldCreateBuilding() throws Exception {
        Building expectedBuilding = createBuilding("B1");
        expectedBuilding.setId(null);
        String json = objectMapper.writeValueAsString(new BuildingDto(expectedBuilding));

        given(buildingDao.save(any())).willReturn(expectedBuilding);

        mockMvc.perform(post("/api/buildings").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("B1"));
    }

    @Test
    void shouldDeleteBuilding() throws Exception {
        mockMvc.perform(delete("/api/buildings/999"))
                .andExpect(status().isOk());
    }

    private Building createBuilding(String name) {
        return new Building(name);
    }
}
