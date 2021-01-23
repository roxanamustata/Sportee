package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.GymClassBookingDTO;
import com.sportee.sportee.data.DTO.GymClassDTO;
import com.sportee.sportee.data.DTO.MeasurementDTO;
import com.sportee.sportee.data.DTO.UserDTO;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IGymClassBookingService {
    List<GymClassBookingDTO> getAllGymClassBookings();

//int id, LocalDateTime bookingDate, boolean valid, GymClass gymClass, User user
    void insertGymClassBooking(Integer gymClassId, Integer userId);

    void deleteGymClassBooking(Integer id);

//    void bookGymClass(GymClass gymClass, User user);

    void editGymClassBooking(Integer id, Optional<Boolean> value, Optional<GymClass> gymClass, Optional<User> user);

    void bookGymClassBooking(Integer gymClass, String remoteUserName);
}
