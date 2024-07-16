package com.practice.airline.repository;

import com.practice.airline.domain.SeatStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatStatusRepository extends FindRepository<SeatStatus, Long> {

}
