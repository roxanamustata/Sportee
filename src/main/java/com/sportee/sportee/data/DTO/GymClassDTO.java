package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.GymClass;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
public class GymClassDTO {

    private int id;
    private LocalDateTime date;
    private String gymClassType;
    private String room;


    public GymClassDTO(GymClass gymClass) {
        this.id = gymClass.getId();
        this.date = gymClass.getDate();
        this.gymClassType = gymClass.getGymClassType().getName();
        this.room = gymClass.getRoom().getName();
    }

    public GymClassDTO() {
    }
}
