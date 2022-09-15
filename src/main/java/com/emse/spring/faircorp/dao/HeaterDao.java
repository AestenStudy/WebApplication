package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    @Query("select c from Heater c where c.id=:id")
    Heater getReferenceById(@Param("id") long id);

    /*
    @Modifying
    @Query("delete from Heater c where c.room_id=:id")
    Long deleteByRoomId(Long id);
    */
}