package com.emse.spring.faircorp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;

    @Column
    private Double currentTemp;

    @Column
    private Double targetTemp;

    @OneToMany(mappedBy = "room")
    private List<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private List<Window> windows;

    @ManyToOne
    private Building building;

    public Room() {
    }

    public Room(@NonNull String name, @NonNull Integer floor, @NonNull Building building) {
        this.name = name;
        this.floor = floor;
        this.building = building;
    }

    public Room(@NonNull String name, @NonNull Integer floor) {
        this.name = name;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public Double getCurrentTemp() {
        return currentTemp;
    }

    public Double getTargetTemp() {
        return targetTemp;
    }

    public List<Heater> getHeaters() {
        return heaters;
    }

    public List<Window> getWindows() {
        return windows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setCurrentTemp(Double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public void setTargetTemp(Double targetTemp) {
        this.targetTemp = targetTemp;
    }

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public void setWindows(List<Window> windows) {
        this.windows = windows;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
