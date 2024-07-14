package com.practice.airline.service;

import com.practice.airline.DTO.HumanDto;
import com.practice.airline.domain.Human;

import java.util.List;

public interface IHumanService {
    void registerHuman(HumanDto humanDto);
    HumanDto findById(Long id);
    List<Human> findHumanByStatus(String status);
    void updateHuman(HumanDto humanDto);
}
