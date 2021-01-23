package com.sportee.sportee.data.DTO;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class HourDTO {

    String hour;
    GymClassDTO[] classes;

    public HourDTO(String hour) {
        this.hour = hour;
        this.classes = new GymClassDTO[7];
        Arrays.fill(classes, new GymClassDTO());
    }

    public void addGymClass(GymClassDTO gymClass) {
        int day = gymClass.getDate().getDayOfWeek().getValue();
        classes[day - 1] = gymClass;
    }

}
