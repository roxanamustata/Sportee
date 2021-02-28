package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.GymClassBookingKey;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class GymClassBookingDTO {

    private GymClassBookingKey id;
    private LocalDateTime gymClassDate;
    private String gymClassType;
    private String user;


    public GymClassBookingDTO(GymClassBooking gymClassBooking) {
        this.id = gymClassBooking.getId();
        this.gymClassDate = gymClassBooking.getGymClass().getDate();
        this.gymClassType = gymClassBooking.getGymClass().getGymClassType().getName();
        this.user = gymClassBooking.getUser().getFirstName() + " " + gymClassBooking.getUser().getLastName();


    }


}
