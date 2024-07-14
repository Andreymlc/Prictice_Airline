package com.practice.airline.controller;

import com.practice.airline.DTO.BookingDto;
import com.practice.airline.exepction.EntityNotFoundException;
import com.practice.airline.exepction.NoAvailableSeatsException;
import com.practice.airline.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/booking")
public class BookingController {

    BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody BookingDto bookingDto) {
        try {
            bookingService.addBooking(bookingDto);
            return ResponseEntity.ok("Покупка успешно совершена. Опыт уже начислился на ваш счёт");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (NoAvailableSeatsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
