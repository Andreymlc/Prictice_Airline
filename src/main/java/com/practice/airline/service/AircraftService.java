package com.practice.airline.service;

import com.practice.airline.DTO.AddAircraftDto;
import com.practice.airline.DTO.AircraftDto;
import com.practice.airline.domain.Aircraft;
import com.practice.airline.excepction.EntityNotFoundException;
import com.practice.airline.excepction.InvalidFormatException;
import com.practice.airline.repository.AircraftRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AircraftService implements IAircraftService {

    private final AircraftRepository aircraftRepo;
    private final ModelMapper modelMapper;

    private static final String ON_BOARD_NUMBER_REGEX = "^[A-Z]{1,2}-?\\d{1,5}[A-Z]{0,2}$";

    public AircraftService(AircraftRepository aircraftRepo, ModelMapper modelMapper) {
        this.aircraftRepo = aircraftRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AircraftDto getDtoById(Long id) {
        Aircraft aircraft = aircraftRepo.findById(id);
        if (aircraft == null) {
            throw new EntityNotFoundException("Воздушное судно с ID " + id + " не найдено");
        }
        return modelMapper.map(aircraft, AircraftDto.class);
    }

    @Override
    public AircraftDto addAircraft(AddAircraftDto addAircraftDto) {
        String onBoardNumber = addAircraftDto.getOnBoardNumber();
        if (!Pattern.matches(ON_BOARD_NUMBER_REGEX, onBoardNumber)) {
            throw new InvalidFormatException("Некорректный формат бортового номера: " + onBoardNumber);
        }

        int capacity = addAircraftDto.getCapacity();
        if (capacity <= 0) {
            throw new InvalidFormatException("Вместимост не может быть меньше или равна 0");
        }

        Aircraft aircraft = new Aircraft(
                onBoardNumber,
                addAircraftDto.getModel(),
                capacity
        );

        aircraftRepo.save(aircraft);
        return modelMapper.map(aircraft, AircraftDto.class);
    }
}
