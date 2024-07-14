package com.practice.airline.service;

import com.practice.airline.DTO.HumanDto;
import com.practice.airline.domain.Human;
import com.practice.airline.domain.Status;
import com.practice.airline.exepction.InvalidFormatException;
import com.practice.airline.repository.HumanRepository;
import com.practice.airline.repository.StatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Transactional
public class HumanService {

    private final HumanRepository humanRepo;
    private final StatusRepository statusRepo;

    private static final String FULL_NAME_REGEX = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+( [А-ЯЁ][а-яё]+)?$";
    private static final String PHONE_NUMBER_REGEX = "^8\\d{10}$";
    private static final String PASSPORT_ID_REGEX = "^\\d{10}";

    public HumanService(HumanRepository humanRepo, StatusRepository statusRepo) {
        this.humanRepo = humanRepo;
        this.statusRepo = statusRepo;
    }

    public List<Human> getAllHumans() {
        return humanRepo.findAll();
    }

    public Human getById(Long id) {
        return humanRepo.findById(id);
    }

    public List<Human> getHumanByStatus(String status) {
        return humanRepo.findByStatus(status);
    }

    public void registerHuman(HumanDto humanDto) {
        String fullName = humanDto.getFullName();
        String phoneNumber = humanDto.getPhoneNumber();
        String passportId = humanDto.getPassportId();

        if (!Pattern.matches(FULL_NAME_REGEX, fullName)) {
            throw new InvalidFormatException("Некорректный формат имени: " + fullName);
        }
        if (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber)) {
            throw new InvalidFormatException("Некорректный формат номера телефона: " + phoneNumber);
        }
        if (!Pattern.matches(PASSPORT_ID_REGEX, passportId)) {
            throw new InvalidFormatException("Некорректный формат серии и номера паспорта: " + passportId);
        }
        Status status = statusRepo.findById(1L);
        Human human = new Human(fullName, humanDto.getPhoneNumber(), humanDto.getPassportId(), status, 1000, 0);
        humanRepo.save(human);
    }

    public void updateStatus(Human human) {
        int experience = human.getExperience();
        Status currentStatus = human.getStatus();
        Status newStatus = null;

        if (experience < 3000 && currentStatus.getId() != 1) {
            newStatus = statusRepo.findById(1L);
        } else if (experience < 5000 && currentStatus.getId() != 2) {
            newStatus = statusRepo.findById(2L);
        } else if (experience < 7500 && currentStatus.getId() != 3) {
            newStatus = statusRepo.findById(3L);
        } else if (currentStatus.getId() != 4) {
            newStatus = statusRepo.findById(4L);
        }
        humanRepo.updateStatus(newStatus, human.getId());
    }

    public void updateExperience(Human human, int bonus) {
        int experience = human.getExperience() + bonus;
        humanRepo.updateExperience(experience, human.getId());
    }
}
