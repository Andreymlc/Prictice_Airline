package com.practice.airline.controller;

import com.practice.airline.DTO.AddBookingDto;
import com.practice.airline.DTO.BookingDto;
import com.practice.airline.exсepction.EntityNotFoundException;
import com.practice.airline.service.IBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public BookingDto addBooking(@RequestBody AddBookingDto addBookingDto) {
        return bookingService.addBooking(addBookingDto);
    }

    @GetMapping("/getAll")
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/getById/{id}")
    public BookingDto getBookingById(@PathVariable Long id) {
        return bookingService.getDtoById(id);
    }
}
