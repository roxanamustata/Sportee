package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClassType;
import com.sportee.sportee.data.DTO.GymClassTypeDTO;
import com.sportee.sportee.data.DTO.MeasurementTypeDTO;

import java.util.List;

public interface IGymClassTypeService {

    List<GymClassTypeDTO> getAllGymClassTypes();

    void insertGymClassType(String name, int duration);

    void deleteGymClassType(Integer id);
}
