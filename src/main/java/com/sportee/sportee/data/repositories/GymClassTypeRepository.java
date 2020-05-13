package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GymClassTypeRepository extends CrudRepository<GymClassType, Integer> {
    Optional<GymClassType> findById(Optional<Integer> gymClassTypeId);
}
