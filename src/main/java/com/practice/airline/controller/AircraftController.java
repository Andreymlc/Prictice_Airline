package com.practice.airline.controller;

import com.practice.airline.DTO.AddAircraftDto;
import com.practice.airline.DTO.AircraftDto;
import com.practice.airline.service.AircraftService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/getById/{id}")
    public AircraftDto getById(@PathVariable Long id) {
        return aircraftService.getDtoById(id);
    }

    @PostMapping("/add")
    public AircraftDto add(@RequestBody AddAircraftDto addAircraftDto) {
        return aircraftService.addAircraft(addAircraftDto);
    }
}
