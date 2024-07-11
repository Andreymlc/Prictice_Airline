package com.practice.airline.repository;

import com.practice.airline.domain.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends JpaRepository<SeatStatus, Integer> {

}
