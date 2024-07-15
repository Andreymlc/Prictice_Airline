package com.practice.airline.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface FindRepository<T, Long> extends Repository<T, Long> {
    @Query("select t from #{#entityName} t order by t.id")
    List<T> findAll();

    @Query("select t from #{#entityName} t where t.id = :id")
    T findById(@Param("id") Long id);
}
