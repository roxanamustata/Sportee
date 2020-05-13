package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DTO.MeasurementTypeDTO;

import java.util.List;
import java.util.Optional;

public interface IMeasurementTypeService {
    List<MeasurementTypeDTO> getAllMeasurementTypes();

    void insertMeasurementType(String name, String unit);

    void deleteMeasurementType(Integer id);

    void editMeasurementType(Integer id, Optional<String> name, Optional<String> unit);



    Optional<MeasurementType> findMeasurementTypeById(Integer id);
}
