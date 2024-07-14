package com.practice.airline.repository;

import com.practice.airline.domain.Booking;
import com.practice.airline.domain.Human;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends FindRepository<Booking, Long> {
    void save(Booking booking);

    @Query(value = "select b.human from Booking b where b.id = :id")
    Human findAllHumanByBooking(@Param("id") Long id);
}
