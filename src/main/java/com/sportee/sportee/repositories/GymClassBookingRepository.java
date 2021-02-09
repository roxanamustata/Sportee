package com.sportee.sportee.repositories;

import com.sportee.sportee.data.DAO.GymClassBooking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymClassBookingRepository extends CrudRepository<GymClassBooking, Integer> {


}
