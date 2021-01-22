package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.GymClassType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GymClassBookingRepository extends CrudRepository<GymClassBooking, Integer> {


}
