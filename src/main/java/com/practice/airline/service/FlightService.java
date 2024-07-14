package com.practice.airline.service;

import com.practice.airline.DTO.FlightDto;
import com.practice.airline.domain.Aircraft;
import com.practice.airline.domain.Airport;
import com.practice.airline.domain.Flight;
import com.practice.airline.exepction.EntityNotFoundException;
import com.practice.airline.exepction.InvalidFormatException;
import com.practice.airline.exepction.NoAvailableSeatsException;
import com.practice.airline.repository.AircraftRepository;
import com.practice.airline.repository.AirportRepository;
import com.practice.airline.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public void updateCntSeats(Flight flight, Long seats) {
        if (seats == 1){
            if (flight.getCntEconomySeat() == 0) throw new NoAvailableSeatsException();
            flightRepository.updateCntEconomySeat(flight.getCntEconomySeat() - 1, flight.getId());
        }
        else {
            if (flight.getCntBusinessSeat() == 0) throw new NoAvailableSeatsException();
            flightRepository.updateCntBusinessSeat(flight.getCntBusinessSeat() - 1, flight.getId());
        }
    }

    public void addFlight(FlightDto flightDto) {
        Aircraft aircraft = aircraftRepository.findById(flightDto.getAircraft_id());

        if (aircraft == null) {
            throw new EntityNotFoundException("Воздушное судно с ID " + flightDto.getAircraft_id() + " не найдено");
        }

        Airport departureAirport = airportRepository.findById(flightDto.getDepartureAirportId());

        if (departureAirport == null) {
            throw new EntityNotFoundException("Аэропорт с ID " + flightDto.getDepartureAirportId() + " не найден");
        }

        Airport arrivalAirport = airportRepository.findById(flightDto.getArrivalAirportId());

        if (arrivalAirport == null) {
            throw new EntityNotFoundException("Аэропорт с ID " + flightDto.getArrivalAirportId() + " не найден");
        }

        LocalDateTime departureDate = flightDto.getDepartureDate();
        LocalDateTime arrivalDate = flightDto.getArrivalDate();
        int price = flightDto.getPrice();

        if (price <= 0) {
            throw new InvalidFormatException("Некорректный формат цены: " + price);
        }

        Flight flight = new Flight(aircraft, departureAirport, arrivalAirport, departureDate, arrivalDate, price, 138, 20);
        flightRepository.save(flight);
    }
}
