package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DTO.GymClassDTO;
import com.sportee.sportee.data.DTO.HourDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface IGymClassService {

    List<GymClassDTO> getAllGymClasses();

    void insertGymClass(LocalDateTime date, int gymClassTypeId, int roomId);

    GymClass findGymClass(Integer id);

    void deleteGymClass(Integer id);


    HourDTO[] getTimetable();

    void editGymClass(Integer id, LocalDateTime date, Integer gymClassTypeId, Integer roomId);

    List<GymClassDTO> searchGymClasses(String keyword);
}

