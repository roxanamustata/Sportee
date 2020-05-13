package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.GymClassType;
import lombok.Getter;

@Getter
public class GymClassTypeDTO {

    private int id;
    private String name;
    private int duration;

    public GymClassTypeDTO(GymClassType gymClassType) {
        this.name = gymClassType.getName();
        this.duration = gymClassType.getDuration();
    }

}
