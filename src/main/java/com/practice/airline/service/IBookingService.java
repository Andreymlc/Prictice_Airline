package com.practice.airline.service;

import com.practice.airline.DTO.AddBookingDto;
import com.practice.airline.DTO.BookingDto;
import com.practice.airline.domain.Booking;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBookingService {
    List<BookingDto> getAllBookings();

    Booking getById(Long id);

    BookingDto getDtoById(Long id);

    @Transactional
    BookingDto addBooking(AddBookingDto addBookingDto);
}
