package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.SubscriptionType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.SubscriptionDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ISubscriptionService {

    List<SubscriptionDTO> getAllSubscriptions();

    void insertSubscription(Date date, boolean valid, int subscriptionType, int user);

    void deleteSubscription(Integer id);

    void editSubscription(Integer id, Optional<Date> date, Optional<Boolean> valid);
}
