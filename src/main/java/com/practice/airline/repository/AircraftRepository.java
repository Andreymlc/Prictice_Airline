package com.practice.airline.repository;

import com.practice.airline.domain.Aircraft;
import com.practice.airline.domain.Airport;
import com.practice.airline.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
    @Query(value = "select a from Aircraft a where a.onBoardNumber = :onBoardNumber")
    List<Aircraft> findByOnBoardNumber(@Param("onBoardNumber") String onBoardNumber);

}
