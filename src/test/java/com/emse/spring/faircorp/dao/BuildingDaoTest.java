package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {
    @Autowired
    private BuildingDao buildingDao;

    @Test
    public void shouldFindABuilding() {
        Building building = buildingDao.findById(-10L);
        Assertions.assertThat(building.getName()).isEqualTo("Building1");
    }

    @Test
    public void shouldFindAllWindows() {
        List<Window> windows = buildingDao.findAllWindowsByBuilding(-10L);
        Assertions.assertThat(windows).hasSize(4);
    }

    @Test
    public void shouldFindAllHeaters() {
        List<Heater> heaters = buildingDao.findAllHeatersByBuilding(-10L);
        Assertions.assertThat(heaters).hasSize(2);
    }
}
