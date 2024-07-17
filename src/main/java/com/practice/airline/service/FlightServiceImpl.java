package com.practice.airline.service;

import com.practice.airline.DTO.AddFlightDto;
import com.practice.airline.DTO.FlightDto;
import com.practice.airline.domain.Aircraft;
import com.practice.airline.domain.Airport;
import com.practice.airline.domain.Flight;
import com.practice.airline.excepction.EntityNotFoundException;
import com.practice.airline.excepction.InvalidFormatException;
import com.practice.airline.excepction.NoAvailableSeatsException;
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
public class FlightServiceImpl implements IFlightService {

    private final FlightRepository flightRepo;
    private final AircraftRepository aircraftRepo;
    private final AirportRepository airportRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepo, AircraftRepository aircraftRepo, AirportRepository airportRepo, ModelMapper modelMapper) {
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
        Flight flight = flightRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Рейс с ID " + id + " не найден"));
        return modelMapper.map(flight, FlightDto.class);
    }

    @Override
    public Flight getById(Long id) {
        return flightRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Рейс с ID " + id + " не найден"));
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
        Aircraft aircraft = aircraftRepo.findById(addFlightDto.getAircraft_id())
                .orElseThrow(() -> new EntityNotFoundException("Воздушное судно с ID " + addFlightDto.getAircraft_id() + " не найдено"));

        Airport departureAirport = airportRepo.findById(addFlightDto.getDepartureAirportId())
                .orElseThrow(() -> new EntityNotFoundException("Аэропорт с ID " + addFlightDto.getDepartureAirportId() + " не найден"));

        Airport arrivalAirport = airportRepo.findById(addFlightDto.getArrivalAirportId())
                .orElseThrow(() -> new EntityNotFoundException("Аэропорт с ID " + addFlightDto.getArrivalAirportId() + " не найден"));


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
        if (flights.isEmpty()) {
            throw new EntityNotFoundException("Воздушное судно не выполняло рейсов или отсутсвует");
        }
        return flights.stream()
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .collect(Collectors.toList());
    }
}
