package com.practice.airline.repository;

import com.practice.airline.domain.Human;
import com.practice.airline.domain.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface HumanRepository extends FindRepository<Human, Long>, CreateRepository<Human> {

    @Query(value = "select h from Human h join h.status s where s.id = :statusId order by h.id")
    List<Human> findByStatus(@Param("statusId") Long statusId);

    @Query(value = "select h.status from Human h join h.status s where h.id = :id")
    Status findStatus(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Human h set h.status = :status where h.id = :id")
    void updateStatus(@Param("status") Status status, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Human h set h.experience = :experience where h.id = :id")
    void updateExperience(@Param("experience") int experience, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "update Human h set h.points = :points where h.id = :id")
    void updatePoints(@Param("points") int points, @Param("id") Long id);
}
