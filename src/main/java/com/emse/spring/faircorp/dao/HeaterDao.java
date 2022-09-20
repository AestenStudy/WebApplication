package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    Heater findById(long id);
    Long deleteByRoomId(Long id);
}