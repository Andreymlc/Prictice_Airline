package com.practice.airline.controller;

import com.practice.airline.DTO.AddFlightDto;
import com.practice.airline.DTO.FlightDto;
import com.practice.airline.service.IFlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final IFlightService flightService;

    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public FlightDto addFlight(@RequestBody AddFlightDto addFlightDto) {
        return flightService.addFlight(addFlightDto);
    }

    @GetMapping("/getAll")
    public List<FlightDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/getById/{id}")
    public FlightDto getAllFlights(@PathVariable Long id) {
        return flightService.getDtoById(id);
    }

    @GetMapping("/getFlightsByAircraft/{id}")
    public List<FlightDto> getFlightsById(@PathVariable Long id) {
        return flightService.getFlightsByAircraft(id);
    }
}
