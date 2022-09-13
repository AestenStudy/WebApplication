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
    private int floor;

    private double currentTemp;

    private double targetTemp;

    @OneToMany(mappedBy = "room")
    private List<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private List<Window> windows;

    public Room() {
    }

    public Room(@NonNull String name, @NonNull int floor) {
        this.name = name;
        this.floor = floor;
    }

}
