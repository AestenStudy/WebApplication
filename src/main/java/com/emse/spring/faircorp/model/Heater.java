package com.emse.spring.faircorp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "HEATER")
public class Heater {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Long power;

    @ManyToOne
    private Room room;

    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    public Heater() {
        new Heater("unnamed", HeaterStatus.OFF, 0, new Room());
    }

    public Heater(@NonNull String name, @NonNull HeaterStatus status, long power, @NonNull Room room) {
        this.name = name;
        this.heaterStatus = status;
        this.power = power;
        this.room = room;
    }

    public Heater(@NonNull String name, @NonNull HeaterStatus heaterStatus, @NonNull Room room) {
        this.name = name;
        this.heaterStatus = heaterStatus;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public HeaterStatus getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPower() {
        return power;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setPower(Long power) {
        this.power = power;
    }
}
