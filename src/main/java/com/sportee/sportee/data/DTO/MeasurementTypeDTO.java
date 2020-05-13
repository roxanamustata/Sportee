package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.MeasurementType;
import lombok.Getter;

@Getter
public class MeasurementTypeDTO {
    private int id;

    private String name;
    private String unit;

    public MeasurementTypeDTO(MeasurementType measurementType) {
        this.id=measurementType.getId();
        this.name = measurementType.getName();
        this.unit = measurementType.getUnit();
    }
}
