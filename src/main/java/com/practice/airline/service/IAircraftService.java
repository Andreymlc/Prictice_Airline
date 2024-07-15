package com.practice.airline.service;

import com.practice.airline.DTO.AddAircraftDto;
import com.practice.airline.DTO.AircraftDto;

public interface IAircraftService {
    AircraftDto getDtoById(Long id);

    AircraftDto addAircraft(AddAircraftDto addAircraftDto);
}
