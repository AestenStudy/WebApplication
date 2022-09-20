package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BuildingDao extends JpaRepository<Building, Long>, BuildingDaoCustom {
    @Query("select b from Building b where b.id=:id")
    Building getReferenceById(@Param("id") long id);
}
