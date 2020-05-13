package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Optional<Room> findById(Optional<Integer> roomId);
}
