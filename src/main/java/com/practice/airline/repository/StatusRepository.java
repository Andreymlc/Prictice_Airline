package com.practice.airline.repository;

import com.practice.airline.domain.Status;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends FindRepository<Status, Long> {

}
