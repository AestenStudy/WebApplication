package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindowDao extends JpaRepository<Window, Long>, WindowDaoCustom {
    @Query("select c from Window c where c.id=:id")
    Window getReferenceById(@Param("id") long id);

    @Query(value = "DELETE FROM Window c where c.room_id =:id", nativeQuery = true) //DEATH TO SQL
    void deleteByRoom(Long id);
}
