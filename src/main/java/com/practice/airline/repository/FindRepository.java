package com.practice.airline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface FindRepository<T, ID> extends Repository<T, ID> {
    Optional<T> findById(ID id);

    @Query(value = "SELECT t FROM #{#entityName} t ORDER BY t.id")
    List<T> findAll();
}
