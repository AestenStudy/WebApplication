package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class RoomDaoCustomImpl implements RoomDaoCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public Room getRoomById(Long id) {
        String jpql = "select r from Room r where r.id = :id";
        return em.createQuery(jpql, Room.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
