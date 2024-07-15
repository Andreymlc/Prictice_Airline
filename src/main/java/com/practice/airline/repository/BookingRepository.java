package com.practice.airline.repository;

import com.practice.airline.domain.Booking;
import com.practice.airline.domain.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends FindRepository<Booking, Long>, CreateRepository<Booking> {

    @Query(value = "select b.human from Booking b where b.id = :id  order by b.id")
    List<Human> findAllHumanByBooking(@Param("id") Long id);
}
