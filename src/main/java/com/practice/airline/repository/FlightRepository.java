package com.practice.airline.repository;

import com.practice.airline.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("select f from Flight f join f.aircraft_id a where a.onBoardNumber = :onBoardNumber")
    List<Flight> findFlightsByOnBoardNumber(@Param("onBoardNumber") String onBoardNumber);
}
