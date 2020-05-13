package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.Measurement;
import lombok.Getter;

import java.sql.Date;

@Getter
public class MeasurementDTO {
    private int id;

    private Date date;
    private int value;

    private String measurementType;
    private String unit;
    private String user;

    public MeasurementDTO(Measurement measurement) {
        this.id = measurement.getId();
        this.date = measurement.getDate();
        this.value = measurement.getValue();
        this.unit = measurement.getMeasurementType().getUnit();
        this.measurementType = measurement.getMeasurementType().getName();
        this.user = measurement.getUser().getFirstName()+" "+measurement.getUser().getLastName();
    }
}
