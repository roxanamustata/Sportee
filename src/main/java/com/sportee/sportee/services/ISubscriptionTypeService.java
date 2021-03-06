package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.SubscriptionTypeDTO;

import java.util.List;

public interface ISubscriptionTypeService {

    List<SubscriptionTypeDTO> getAllSubscriptionType();

    void insertSubscriptionType(String name, int duration, int price);
}
