package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.GymClassDTO;
import com.sportee.sportee.data.DTO.SubscriptionDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ISubscriptionService {

    List<SubscriptionDTO> getAllSubscriptions();

    void insertSubscription(Date date, boolean valid, int subscriptionType, int user);

    void deleteSubscription(Integer id);

    void editSubscription(Integer id, Optional<Date> date, Optional<Boolean> valid);

    List<SubscriptionDTO> searchSubscriptions(String keyword);
}
