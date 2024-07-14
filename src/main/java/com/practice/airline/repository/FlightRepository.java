package com.practice.airline.repository;

import com.practice.airline.domain.Flight;
import com.practice.airline.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FlightRepository extends FindRepository<Flight, Long> {
    void save(Flight flight);

    @Query("select f from Flight f join f.aircraftId a where a.onBoardNumber = :onBoardNumber")
    List<Flight> findFlightsByOnBoardNumber(@Param("onBoardNumber") String onBoardNumber);

    @Modifying
    @Transactional
    @Query(value = "update Flight f set f.cntEconomySeat = :count where f.id = :id")
    void updateCntEconomySeat(@Param("count") int count, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Flight f set f.cntBusinessSeat = :count where f.id = :id")
    void updateCntBusinessSeat(@Param("count") int count, @Param("id") Long id);
}
