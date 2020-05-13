package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.GymClassDTO;
import com.sportee.sportee.data.DTO.HourDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IGymClassService {
    List<GymClassDTO> getAllGymClasses();

    void insertGymClass(LocalDateTime date, int gymClassTypeId, int roomId);

    void deleteGymClass(Integer id);

    HourDTO[] getTimetable();

    void editGymClass(Integer id, Optional<LocalDateTime> date, Optional<Integer> gymClassTypeId, Optional<Integer> roomId);
}

