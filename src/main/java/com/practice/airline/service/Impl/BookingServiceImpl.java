package com.practice.airline.service.Impl;

import com.practice.airline.DTO.AddBookingDto;
import com.practice.airline.DTO.BookingDto;
import com.practice.airline.domain.*;
import com.practice.airline.excepction.EntityNotFoundException;
import com.practice.airline.excepction.InvalidFormatException;
import com.practice.airline.repository.BookingRepository;
import com.practice.airline.repository.SeatStatusRepository;
import com.practice.airline.service.IBookingService;
import com.practice.airline.service.IFlightService;
import com.practice.airline.service.IHumanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepo;
    private final SeatStatusRepository seatStatusRepo;
    private final IHumanService humanService;
    private final IFlightService flightService;
    private final ModelMapper modelMapper;

    private static final float EXPERIENCE_BONUS = 0.1f;
    public static final float MAX_BONUS_PERCENTAGE = 0.3f;

    public BookingServiceImpl(BookingRepository bookingRepo, IHumanService humanService, IFlightService flightService, SeatStatusRepository seatStatusRepo, ModelMapper modelMapper) {
        this.bookingRepo = bookingRepo;
        this.humanService = humanService;
        this.flightService = flightService;
        this.seatStatusRepo = seatStatusRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Бронирование с ID " + id + " не найдено"));
    }

    @Override
    public BookingDto getDtoById(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Бронирование с ID " + id + " не найдено"));
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    @Transactional
    public BookingDto addBooking(AddBookingDto addBookingDto) {
        Human human = humanService.getById(addBookingDto.getHumanId());

        Flight flight = flightService.getById(addBookingDto.getFlightId());

        SeatStatus seatStatus = seatStatusRepo.findById(addBookingDto.getSeatStatusId())
                .orElseThrow(() -> new EntityNotFoundException("Класс с ID " + addBookingDto.getSeatStatusId() + " не найден"));


        int points = addBookingDto.getPoints();

        if (points < 0) {
            throw new InvalidFormatException("Некорректное значение бонусов");
        }

        int currentPoints = human.getPoints();

        if (currentPoints < points) {
            throw new InvalidFormatException("Недостаточно бонусов. Количество бонусов: " + currentPoints);
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        int price = (int) Math.round((flight.getPrice() + seatStatus.getPrice()) * human.getStatus().getDiscount());

        if (points > price * MAX_BONUS_PERCENTAGE) {
            points = Math.round((price * MAX_BONUS_PERCENTAGE));
        }

        price -= points;
        Booking booking = new Booking(
                human,
                flight,
                localDateTime,
                seatStatus,
                price,
                points
        );

        points -= (int) Math.round(price * human.getStatus().getCoefficientPoints());

        bookingRepo.save(booking);
        flightService.updateCntSeats(flight, seatStatus.getId());
        int newExperience = human.getExperience() + Math.round(price * EXPERIENCE_BONUS);
        humanService.updateExperience(human, newExperience);
        humanService.updatePoints(human, currentPoints - points);
        humanService.updateStatus(human, newExperience);

        return modelMapper.map(booking, BookingDto.class);
    }
}
