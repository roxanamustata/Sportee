package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GymClassRepository extends CrudRepository<GymClass, Integer> {

    List<GymClass> findAllByDateBetween(LocalDateTime startDate, LocalDateTime endDate);


}
