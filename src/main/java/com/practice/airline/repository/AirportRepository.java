package com.practice.airline.repository;

import com.practice.airline.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends FindRepository<Airport, Long> {
    @Query(value = "select a from Airport a where a.iata = :iata  order by a.id")
    List<Airport> findByIata(@Param("iata") String iata);

    @Query(value = "select a from Airport a where a.city = :city order by a.id")
    List<Airport> findByCity(@Param("city") String city);
}
