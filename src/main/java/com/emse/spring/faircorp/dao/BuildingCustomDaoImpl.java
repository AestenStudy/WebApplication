package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuildingCustomDaoImpl implements BuildingDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findAllWindowsByBuilding(Long id) {
        List<Long> roomIds = getRoomIdList(id);
        List<Window> windows = new ArrayList<>();
        for (long roomId : roomIds) {
            String jpql = "select w from Window w where w.room.id = :roomId";
            windows.addAll(
                    em.createQuery(jpql, Window.class)
                    .setParameter("room_id", roomId)
                    .getResultList()
            );
        }
        return windows;
    }

    @Override
    public List<Heater> findAllHeatersByBuilding(Long id) {
        List<Long> roomIds = getRoomIdList(id);
        List<Heater> heaters = new ArrayList<>();
        for (long roomId : roomIds) {
            String jpql = "select h from Heater h where h.room.id = :roomId";
            heaters.addAll(
                    em.createQuery(jpql, Heater.class)
                            .setParameter("room_id", roomId)
                            .getResultList()
            );
        }
        return heaters;
    }

    private List<Long> getRoomIdList(long id) {
        String jpqlRooms = "select r from Room r where r.building.id = :id";
        return em.createQuery(jpqlRooms, Room.class)
                .setParameter("id", id)
                .getResultList().stream().map(Room::getId).collect(Collectors.toList());
    }
}
