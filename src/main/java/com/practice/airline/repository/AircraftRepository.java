package com.practice.airline.repository;

import com.practice.airline.domain.Aircraft;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends FindRepository<Aircraft, Long>, CreateRepository<Aircraft> {
    @Query(value = "select a from Aircraft a where a.onBoardNumber = :onBoardNumber  order by a.id")
    List<Aircraft> findByOnBoardNumber(@Param("onBoardNumber") String onBoardNumber);
}
