package com.practice.airline.service;

import com.practice.airline.DTO.AddHumanDto;
import com.practice.airline.DTO.HumanDto;
import com.practice.airline.DTO.StatusDto;
import com.practice.airline.domain.Human;

import java.util.List;

public interface IHumanService {
    List<HumanDto> getAllHumans();

    Human getById(Long id);

    HumanDto getDtoById(Long id);

    StatusDto getStatus(Long id);

    List<HumanDto> getHumanByStatus(Long statusId);

    HumanDto registerHuman(AddHumanDto addHumanDto);

    void updateStatus(Human human);

    void updateExperience(Human human, int bonus);

    void updatePoints(Human human, int points);

}
