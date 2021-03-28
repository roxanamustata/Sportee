package com.sportee.sportee.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
//TODO valid should work with true and false instead of 1 and 0
    @Query("SELECT s FROM Subscription s WHERE CONCAT(s.user.firstName, ' ', s.user.lastName,' ',s.date,' ',s.subscriptionType.name,' ', s.valid) LIKE %?1%")
    List<Subscription> search(String keyword);
}
