package com.practice.airline.service;

import com.practice.airline.DTO.AddFlightDto;
import com.practice.airline.DTO.FlightDto;
import com.practice.airline.domain.Flight;

import java.util.List;

public interface IFlightService {
    List<FlightDto> getAllFlights();

    FlightDto getDtoById(Long id);

    Flight getById(Long id);

    void updateCntSeats(Flight flight, Long seats);

    FlightDto addFlight(AddFlightDto addFlightDto);

    List<FlightDto> getFlightsByAircraft(Long id);
}
