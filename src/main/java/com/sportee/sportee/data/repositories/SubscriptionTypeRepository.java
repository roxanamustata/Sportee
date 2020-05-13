package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.SubscriptionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionType, Integer> {
}
