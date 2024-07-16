package com.practice.airline.controller;

import com.practice.airline.DTO.AddHumanDto;
import com.practice.airline.DTO.HumanDto;
import com.practice.airline.DTO.StatusDto;
import com.practice.airline.service.IHumanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/human")
public class HumanController {
    private final IHumanService humanService;

    public HumanController(IHumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/add")
    public HumanDto addHuman(@RequestBody AddHumanDto addHumanDto) {
        return humanService.registerHuman(addHumanDto);
    }

    @GetMapping("/getAll")
    public List<HumanDto> getAllHuman() {
        return humanService.getAllHumans();
    }

    @GetMapping("/getById/{id}")
    public HumanDto getHumanById(@PathVariable Long id) {
        return humanService.getDtoById(id);
    }

    @GetMapping("/getStatus/{id}")
    public StatusDto getStatus(@PathVariable Long id) {
        return humanService.getStatus(id);
    }

    @GetMapping("/getByStatus/{statusId}")
    public List<HumanDto> getHumanByStatus(@PathVariable Long statusId) {
        return humanService.getHumanByStatus(statusId);
    }
}
