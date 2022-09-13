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
}
