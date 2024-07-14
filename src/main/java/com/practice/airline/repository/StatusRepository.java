package com.practice.airline.repository;

import com.practice.airline.domain.Human;
import com.practice.airline.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends FindRepository<Status, Long> {

}
