package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}
