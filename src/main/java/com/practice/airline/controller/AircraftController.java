package com.practice.airline.controller;

import com.practice.airline.DTO.AddAircraftDto;
import com.practice.airline.DTO.AircraftDto;
import com.practice.airline.service.AircraftServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftServiceImpl aircraftServiceImpl;

    public AircraftController(AircraftServiceImpl aircraftServiceImpl) {
        this.aircraftServiceImpl = aircraftServiceImpl;
    }

    @GetMapping("/getById/{id}")
    public AircraftDto getById(@PathVariable Long id) {
        return aircraftServiceImpl.getDtoById(id);
    }

    @GetMapping("/getAll")
    public List<AircraftDto> getAll() {
        return aircraftServiceImpl.getAllAircraft();
    }

    @PostMapping("/add")
    public AircraftDto add(@RequestBody AddAircraftDto addAircraftDto) {
        return aircraftServiceImpl.addAircraft(addAircraftDto);
    }
}
