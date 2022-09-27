package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomDto {
    private Long id;
    private String name;
    private int floor;
    private Double currentTemp;
    private Double targetTemp;
    private List<HeaterDto> heaters;
    private List<WindowDto> windows;
    private String buildingName;
    private Long buildingId;

    public RoomDto() {}

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.currentTemp = room.getCurrentTemp();
        this.targetTemp = room.getTargetTemp();
        this.heaters = room.getHeaters() != null ? room.getHeaters().stream().map(HeaterDto::new).collect(Collectors.toList()) : new ArrayList<>();
        this.windows = room.getWindows() != null ?  room.getWindows().stream().map(WindowDto::new).collect(Collectors.toList()) : new ArrayList<>();
        this.buildingName = room.getBuilding().getName();
        this.buildingId = room.getBuilding().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public Double getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(Double targetTemp) {
        this.targetTemp = targetTemp;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public List<HeaterDto> getHeaters() {
        return heaters;
    }

    public void setHeaters(List<HeaterDto> heaters) {
        this.heaters = heaters;
    }

    public List<WindowDto> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowDto> windows) {
        this.windows = windows;
    }
}
