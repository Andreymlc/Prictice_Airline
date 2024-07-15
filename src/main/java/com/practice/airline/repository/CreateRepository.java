package com.practice.airline.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CreateRepository <T> extends Repository<T, Long> {
    void save(T t);
}
