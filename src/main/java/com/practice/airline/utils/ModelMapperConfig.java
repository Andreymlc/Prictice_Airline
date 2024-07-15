package com.practice.airline.utils;

import com.practice.airline.DTO.BookingDto;
import com.practice.airline.DTO.FlightDto;
import com.practice.airline.domain.Booking;
import com.practice.airline.domain.Flight;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<Flight, FlightDto>() {
            @Override
            protected void configure() {
                map().setFlightId(source.getId());
                map().setOnBoardNumber(source.getAircraftId().getOnBoardNumber());
                map().setDepartureIata(source.getDepartureAirport().getIata());
                map().setArrivalIata(source.getArrivalAirport().getIata());
                map().setDepartureDate(source.getDepartureDate());
                map().setArrivalDate(source.getArrivalDate());
                map().setPrice(source.getPrice());
            }
        });

        modelMapper.addMappings(new PropertyMap<Booking, BookingDto>() {
            @Override
            protected void configure() {
                map().setDate(source.getDate());
                map().setFullName(source.getHuman().getFullName());
                map().setPassportNumber(source.getHuman().getPassportId());
                map().setDepartureIata(source.getFlight().getDepartureAirport().getIata());
                map().setArrivalIata(source.getFlight().getArrivalAirport().getIata());
                map().setSeatStatus(source.getSeatStatus().getName());
                map().setPrice(source.getPrice());
            }
        });

        return modelMapper;
    }
}
