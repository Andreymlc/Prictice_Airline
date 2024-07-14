package com.practice.airline.controller;

import com.practice.airline.DTO.HumanDto;
import com.practice.airline.service.HumanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/human")
public class HumanController {
    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addHuman(@RequestBody HumanDto humanDto) {
        try {
            humanService.registerHuman(humanDto);
            return ResponseEntity.ok("Человек успешно добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления человека: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllHuman() {
        try {
            return ResponseEntity.ok(humanService.getAllHumans());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления человека: " + e.getMessage());
        }
    }


    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<?> getHumanByStatus(@PathVariable  String status) {
        try {
            return ResponseEntity.ok(humanService.getHumanByStatus(status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления человека: " + e.getMessage());
        }
    }
}
