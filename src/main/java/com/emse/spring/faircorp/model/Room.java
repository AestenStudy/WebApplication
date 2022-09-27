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
    private Double currentTemp; //null value can't be assigned to primitive type double, so Double

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

    public Room(@NonNull String name, @NonNull int floor, @NonNull Building building) {
        this.name = name;
        this.floor = floor;
        this.building = building;
    }

    public Room(@NonNull String name, @NonNull int floor) {
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

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
