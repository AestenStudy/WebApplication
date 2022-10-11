package com.emse.spring.faircorp.web;

import com.emse.spring.faircorp.api.RoomController;
import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = RoomController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RoomDao roomDao;

    @MockBean
    private BuildingDao buildingDao;

    @Test
    void shouldLoadRooms() throws Exception {
        given(roomDao.findAll()).willReturn(List.of(
                createRoom("R1"),
                createRoom("R2")
        ));

        mockMvc.perform(get("/api/rooms").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("[*].name").value(containsInAnyOrder("R1", "R2")));
    }

    @Test
    void shouldLoadARoomAndReturnNullIfNotFound() throws Exception {
        given(roomDao.findById(999L)).willReturn(new Room());

        mockMvc.perform(get("/api/rooms/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(content().string(""));
    }

    @Test
    void shouldLoadARoom() throws Exception {
        //use of any() instead of 999L otherwise error because equals() method isn't what we expect it to be
        given(roomDao.findById(any())).willReturn(Optional.of(createRoom("R1")));

        mockMvc.perform(get("/api/rooms/999").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                // the content can be tested with Json path
                .andExpect(jsonPath("$.name").value("R1"));
    }

    @Test
    void shouldCreateRoom() throws Exception {
        Room expectedRoom = createRoom("R1");
        expectedRoom.setId(null);
        String json = objectMapper.writeValueAsString(new RoomDto(expectedRoom));

        given(roomDao.save(any())).willReturn(expectedRoom);

        mockMvc.perform(post("/api/rooms").content(json).contentType(APPLICATION_JSON_VALUE))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("R1"));
    }

    @Test
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/999"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldSwitchWindows() throws Exception {
        Room expectedRoom = createRoom("R1");
        Window expectedWindow = createWindow("window 1", expectedRoom);
        expectedRoom.setWindows(List.of(expectedWindow));

        Assertions.assertThat(expectedWindow.getWindowStatus()).isEqualTo(WindowStatus.OPEN);

        given(roomDao.findById(any())).willReturn(Optional.of(expectedRoom));

        mockMvc.perform(put("/api/rooms/999/switchWindows").accept(APPLICATION_JSON))
                // check the HTTP response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.windows[0].name").value("window 1"))
                .andExpect(jsonPath("$.windows[0].windowStatus").value("CLOSED"));
    }

    private Room createRoom(String name) {
        Building building = new Building("B1");
        return new Room(name, 0, building);
    }

    private Window createWindow(String name, Room room) {
        return new Window(name, WindowStatus.OPEN, room);
    }
}
