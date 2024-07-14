package com.practice.airline.service;

import com.practice.airline.DTO.BookingDto;
import com.practice.airline.domain.*;
import com.practice.airline.exepction.EntityNotFoundException;
import com.practice.airline.exepction.InvalidFormatException;
import com.practice.airline.repository.BookingRepository;
import com.practice.airline.repository.SeatStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SeatStatusRepository seatStatusRepository;
    private final HumanService humanService;
    private final FlightService flightService;

    private static final double EXPERIENCE_BONUS = 0.1;

    public BookingService(BookingRepository bookingRepository, HumanService humanService, FlightService flightService, SeatStatusRepository seatStatusRepository) {
        this.bookingRepository = bookingRepository;
        this.humanService = humanService;
        this.flightService = flightService;
        this.seatStatusRepository = seatStatusRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Transactional
    public void addBooking(BookingDto bookingDto) {
        Human human = humanService.getById(bookingDto.getHumanId());

        if (human == null) {
            throw new EntityNotFoundException("Человек с ID " + bookingDto.getHumanId() + " не найден");
        }

        Flight flight = flightService.getFlightById(bookingDto.getFlightId());

        if (flight == null) {
            throw new EntityNotFoundException("Рейс с ID " + bookingDto.getFlightId() + " не найдет");
        }

        SeatStatus seatStatus = seatStatusRepository.findById(bookingDto.getSeatStatusId());

        if (seatStatus == null) {
            throw new EntityNotFoundException("Тариф с ID " + bookingDto.getSeatStatusId() + " не найден");
        }

        int points = bookingDto.getPoints();

        if (points < 0) {
            throw new InvalidFormatException("Некорректное значение бонусов");
        }

        if (human.getPoints() < points) {
            throw new InvalidFormatException("Недостаточно бонусов. Количество бонусов: " + human.getPoints());
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        int price = (int) Math.round((flight.getPrice() + seatStatus.getPrice()) * human.getStatus().getDiscount());

        if (points > price * 0.3) {
            points = (int) Math.round((price * 0.3));
        }

        price -= points;
        Booking booking = new Booking(human, flight, localDateTime, seatStatus, price);
        flightService.updateCntSeats(flight, seatStatus.getId());
        bookingRepository.save(booking);
        int bonus = (int) Math.round(price * EXPERIENCE_BONUS);
        humanService.updateExperience(human, bonus);
        humanService.updateStatus(human);
    }
}
