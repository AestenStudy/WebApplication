package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindowDao extends JpaRepository<Window, Long>, WindowDaoCustom {
    Window findById(long id);

    void deleteByRoomId(Long id);
}
