package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.dao.BuildingDao;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BUILDING")
public class Building {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "building")
    private List<Room> rooms;

    public Building() {}

    public Building(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
