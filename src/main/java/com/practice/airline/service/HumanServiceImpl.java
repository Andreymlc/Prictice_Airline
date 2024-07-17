package com.practice.airline.service;

import com.practice.airline.DTO.AddHumanDto;
import com.practice.airline.DTO.HumanDto;
import com.practice.airline.DTO.StatusDto;
import com.practice.airline.domain.Human;
import com.practice.airline.domain.Status;
import com.practice.airline.excepction.EntityNotFoundException;
import com.practice.airline.excepction.HumanAlreadyExistsException;
import com.practice.airline.excepction.InvalidFormatException;
import com.practice.airline.repository.HumanRepository;
import com.practice.airline.repository.StatusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class HumanServiceImpl implements IHumanService {

    private final HumanRepository humanRepo;
    private final StatusRepository statusRepo;
    private final ModelMapper modelMapper;

    private static final String FULL_NAME_REGEX = "^[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+( [А-ЯЁ][а-яё]+)?$";
    private static final String PHONE_NUMBER_REGEX = "^8\\d{10}$";
    private static final String PASSPORT_ID_REGEX = "^\\d{10}";

    @Autowired
    public HumanServiceImpl(HumanRepository humanRepo, StatusRepository statusRepo, ModelMapper modelMapper) {
        this.humanRepo = humanRepo;
        this.statusRepo = statusRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HumanDto> getAllHumans() {
        List<Human> humans = humanRepo.findAll();
        if (humans.isEmpty()) {
            throw new EntityNotFoundException("Список людей пуст");
        }
        return humans.stream()
                .map(human -> modelMapper.map(human, HumanDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public HumanDto getDtoById(Long id) {
        Human human = humanRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Человек с ID " + id + " не найден"));
        return modelMapper.map(human, HumanDto.class);
    }

    @Override
    public Human getById(Long id) {
        return humanRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Человек с ID " + id + " не найден"));
    }

    @Override
    public StatusDto getStatus(Long id) {
        Status status = humanRepo.findStatus(id);
        return modelMapper.map(status, StatusDto.class);
    }

    @Override
    public List<HumanDto> getHumanByStatus(Long statusId) {
        List<Human> humans = humanRepo.findByStatus(statusId);
        if (humans.isEmpty()) {
            throw new EntityNotFoundException("Список людей пуст");
        }
        return humans.stream()
                .map(human -> modelMapper.map(human, HumanDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public HumanDto registerHuman(AddHumanDto addHumanDto) {
        String fullName = addHumanDto.getFullName();
        String phoneNumber = addHumanDto.getPhoneNumber();
        String passportId = addHumanDto.getPassportId();

        if (!Pattern.matches(FULL_NAME_REGEX, fullName)) {
            throw new InvalidFormatException("Некорректный формат имени: " + fullName);
        }
        if (!Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber)) {
            throw new InvalidFormatException("Некорректный формат номера телефона: " + phoneNumber);
        }
        if (!Pattern.matches(PASSPORT_ID_REGEX, passportId)) {
            throw new InvalidFormatException("Некорректный формат серии и номера паспорта: " + passportId);
        }

        if (humanRepo.findByPhoneNumber(phoneNumber) != null || humanRepo.findByPassportID(passportId) != null) {
            throw new HumanAlreadyExistsException("Такой человек уже существует");
        }

        Status status = statusRepo.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Статус с ID" + 1 + "не найден"));

        Human human = new Human(
                fullName,
                addHumanDto.getPhoneNumber(),
                addHumanDto.getPassportId(),
                status,
                1000,
                0
        );
        humanRepo.save(human);
        return modelMapper.map(human, HumanDto.class);
    }

    @Transactional
    @Override
    public void updateStatus(Human human, int newExperience) {
        Status currentStatus = human.getStatus();
        Status newStatus = currentStatus;

        if (newExperience < 3000 && currentStatus.getId() != 1) {
            newStatus = statusRepo.findById(1L)
                    .orElseThrow(() -> new EntityNotFoundException("Статус с ID" + 1 + "не найден"));;
        } else if (newExperience >= 3000 && newExperience < 5000 && currentStatus.getId() != 2) {
            newStatus = statusRepo.findById(2L)
                    .orElseThrow(() -> new EntityNotFoundException("Статус с ID" + 2 + "не найден"));;
        } else if (newExperience >= 5000 && newExperience < 7500 && currentStatus.getId() != 3) {
            newStatus = statusRepo.findById(3L)
                    .orElseThrow(() -> new EntityNotFoundException("Статус с ID" + 3 + "не найден"));;
        } else if (newExperience >= 7500 && currentStatus.getId() != 4) {
            newStatus = statusRepo.findById(4L)
                    .orElseThrow(() -> new EntityNotFoundException("Статус с ID" + 4 + "не найден"));;
        }
        humanRepo.updateStatus(newStatus, human.getId());
    }

    @Transactional
    @Override
    public void updateExperience(Human human, int experienceBonus) {
        humanRepo.updateExperience(experienceBonus, human.getId());
    }

    @Transactional
    @Override
    public void updatePoints(Human human, int points) {
        humanRepo.updatePoints(points, human.getId());
    }
}
