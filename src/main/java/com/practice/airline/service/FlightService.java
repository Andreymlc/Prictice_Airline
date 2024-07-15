package com.practice.airline.service;

import com.practice.airline.DTO.AddFlightDto;
import com.practice.airline.DTO.FlightDto;
import com.practice.airline.DTO.HumanDto;
import com.practice.airline.domain.Aircraft;
import com.practice.airline.domain.Airport;
import com.practice.airline.domain.Flight;
import com.practice.airline.exсepction.EntityNotFoundException;
import com.practice.airline.exсepction.InvalidFormatException;
import com.practice.airline.exсepction.NoAvailableSeatsException;
import com.practice.airline.repository.AircraftRepository;
import com.practice.airline.repository.AirportRepository;
import com.practice.airline.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightService implements IFlightService {

    private final FlightRepository flightRepo;
    private final AircraftRepository aircraftRepo;
    private final AirportRepository airportRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public FlightService(FlightRepository flightRepo, AircraftRepository aircraftRepo, AirportRepository airportRepo, ModelMapper modelMapper) {
        this.flightRepo = flightRepo;
        this.aircraftRepo = aircraftRepo;
        this.airportRepo = airportRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FlightDto> getAllFlights() {
        List<Flight> flights = flightRepo.findAll();
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto getDtoById(Long id) {
        Flight flight = flightRepo.findById(id);
        return modelMapper.map(flight, FlightDto.class);
    }

    @Override
    public Flight getById(Long id) {
        return flightRepo.findById(id);
    }

    @Override
    public void updateCntSeats(Flight flight, Long seats) {
        if (seats == 1){
            if (flight.getCntEconomySeat() == 0) throw new NoAvailableSeatsException();
            flightRepo.updateCntEconomySeat(flight.getCntEconomySeat() - 1, flight.getId());
        }
        else {
            if (flight.getCntBusinessSeat() == 0) throw new NoAvailableSeatsException();
            flightRepo.updateCntBusinessSeat(flight.getCntBusinessSeat() - 1, flight.getId());
        }
    }

    @Override
    public FlightDto addFlight(AddFlightDto addFlightDto) {
        Aircraft aircraft = aircraftRepo.findById(addFlightDto.getAircraft_id());

        if (aircraft == null) {
            throw new EntityNotFoundException("Воздушное судно с ID" + addFlightDto.getAircraft_id() + "не найдено");
        }

        Airport departureAirport = airportRepo.findById(addFlightDto.getDepartureAirportId());

        if (departureAirport == null) {
            throw new EntityNotFoundException("Аэропорт с ID" + addFlightDto.getDepartureAirportId() + "не найден");
        }

        Airport arrivalAirport = airportRepo.findById(addFlightDto.getArrivalAirportId());

        if (arrivalAirport == null) {
            throw new EntityNotFoundException("Аэропорт с ID" + addFlightDto.getDepartureAirportId() + "не найден");
        }

        LocalDateTime departureDate = addFlightDto.getDepartureDate();
        LocalDateTime arrivalDate = addFlightDto.getArrivalDate();
        int price = addFlightDto.getPrice();

        if (price <= 0) {
            throw new InvalidFormatException("Некорректный формат цены: " + price);
        }

        Flight flight = new Flight(
                aircraft,
                departureAirport,
                arrivalAirport,
                departureDate,
                arrivalDate,
                price,
                138,
                20
        );
        flightRepo.save(flight);
        return modelMapper.map(flight, FlightDto.class);
    }

    @Override
    public List<FlightDto> getFlightsByAircraft(Long id) {
        List<Flight> flights = flightRepo.findFlightsByAircraft(id);
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }
}
