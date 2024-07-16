package com.practice.airline.repository;

import com.practice.airline.domain.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends FindRepository<Status, Long> {

}
