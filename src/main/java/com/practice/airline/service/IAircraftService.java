package com.practice.airline.service;

import com.practice.airline.DTO.AddAircraftDto;
import com.practice.airline.DTO.AircraftDto;

import java.util.List;

public interface IAircraftService {
    AircraftDto getDtoById(Long id);

    List<AircraftDto> getAllAircraft();

    AircraftDto addAircraft(AddAircraftDto addAircraftDto);
}
