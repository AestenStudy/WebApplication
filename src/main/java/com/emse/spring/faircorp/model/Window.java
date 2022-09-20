package com.emse.spring.faircorp.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "RWINDOW")
public class Window {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WindowStatus windowStatus;

    @ManyToOne
    private Room room;

    public Window() {
        new Window("unnamed", WindowStatus.CLOSED, new Room());
    }

    public Window(@NonNull String name, @NonNull WindowStatus status, @NonNull Room room) {
        this.windowStatus = status;
        this.name = name;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }
}