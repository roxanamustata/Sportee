package com.sportee.sportee.repositories;

import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.GymClassBookingKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymClassBookingRepository extends CrudRepository<GymClassBooking, GymClassBookingKey> {


}
