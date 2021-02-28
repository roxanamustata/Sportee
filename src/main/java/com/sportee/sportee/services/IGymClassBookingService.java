package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.GymClassBookingKey;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.GymClassBookingDTO;

import java.util.List;
import java.util.Optional;

public interface IGymClassBookingService {
    List<GymClassBookingDTO> getAllGymClassBookings();

    void insertGymClassBooking(GymClassBooking gymClassBooking) throws Exception;

    void deleteGymClassBooking(GymClassBookingKey id);

    void editGymClassBooking(Integer id, Optional<Boolean> value, Optional<GymClass> gymClass, Optional<User> user);

    void bookGymClassBooking(Integer gymClass, String remoteUserName);
}
