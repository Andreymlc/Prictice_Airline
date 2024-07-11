package com.practice.airline.repository;

import com.practice.airline.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query(value = "select s from Status s")
    List<Status> findAll();
}
