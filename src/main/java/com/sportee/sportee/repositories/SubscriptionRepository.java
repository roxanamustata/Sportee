package com.sportee.sportee.repositories;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

    @Query("SELECT s FROM Subscription s WHERE CONCAT(s.user.firstName, ' ', s.user.lastName,' '," +
            "s.date,' '," +
            "s.subscriptionType.name,' ', " +
            "case when s.valid = 1  then 'true' when s.valid = 0 then 'false' end ) LIKE %?1%")
    List<Subscription> search(String keyword);


}
