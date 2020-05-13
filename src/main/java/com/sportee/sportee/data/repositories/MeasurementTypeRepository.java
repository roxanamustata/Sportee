package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.MeasurementType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends CrudRepository<MeasurementType, Integer> {
}
