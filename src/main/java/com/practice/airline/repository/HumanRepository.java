package com.practice.airline.repository;

import com.practice.airline.domain.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends JpaRepository<Human, Long> {
    @Query(value = "select h from Human h join h.status s where s.name = :status")
    List<Human> findHumanByStatus(@Param("status") String status);
}
