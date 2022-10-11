package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dto.ApiGouvAddressDto;
import com.emse.spring.faircorp.dto.BuildingDto;
import com.emse.spring.faircorp.dto.RoomDto;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.HeaterStatus;
import com.emse.spring.faircorp.model.WindowStatus;
import com.emse.spring.faircorp.service.AddressSearchService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;

    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto dto) {
        Building building = null;

        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName(), dto.getAddress()));
        }
        else {
            building = buildingDao.getReferenceById(dto.getId());
            building.setName(dto.getName());
            building.setAddress(dto.getAddress());
        }

        return new BuildingDto(building);
    }

    @GetMapping(path = "/{id}")
    public BuildingDto findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDto::new).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        buildingDao.deleteById(id);
    }

    @GetMapping(path = "/{id}/rooms")
    public List<RoomDto> findRooms(@PathVariable Long id) {
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        return building.getRooms().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}/switchWindows")
    public BuildingDto switchWindowStatus(@PathVariable Long id) {
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        building.getRooms().forEach(b -> b.getWindows().forEach(w -> w.setWindowStatus(w.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED: WindowStatus.OPEN)));
        return new BuildingDto(building);
    }

    @PutMapping(path = "/{id}/switchHeaters")
    public BuildingDto switchHeaterStatus(@PathVariable Long id) {
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        building.getRooms().forEach(b -> b.getHeaters().forEach(h -> h.setHeaterStatus(h.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF : HeaterStatus.ON)));
        return new BuildingDto(building);
    }

    @GetMapping("/{id}/address")
    public List<ApiGouvAddressDto> findAddress(@PathVariable Long id) {
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        return new AddressSearchService(new RestTemplateBuilder()).findAddress(List.of(building.getAddress()));
    }
}
