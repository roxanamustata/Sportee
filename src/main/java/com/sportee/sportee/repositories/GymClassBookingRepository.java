package com.sportee.sportee.repositories;

import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.GymClassBookingKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymClassBookingRepository extends CrudRepository<GymClassBooking, GymClassBookingKey> {

    GymClassBooking findByUserIdAndGymClassId(int userId, int gymClassId);

    List<GymClassBooking> findAllByUserId(int userId);


    @Query("SELECT g FROM GymClassBooking g WHERE CONCAT(g.gymClass.date,' ', g.gymClass.gymClassType.name,' ', g.user.firstName, ' ', g.user.lastName ) LIKE %?1%")
    List<GymClassBooking> search(String keyword);
}
