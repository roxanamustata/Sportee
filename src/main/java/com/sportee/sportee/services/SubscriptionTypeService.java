package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.SubscriptionType;
import com.sportee.sportee.data.DTO.SubscriptionTypeDTO;
import com.sportee.sportee.data.repositories.SubscriptionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionTypeService implements ISubscriptionTypeService {
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionTypeDTO> getAllSubscriptionType() {
        List<SubscriptionTypeDTO> subscriptionTypeDTOS = new ArrayList<SubscriptionTypeDTO>();
        Iterable<SubscriptionType> all = subscriptionTypeRepository.findAll();
        all.forEach(s -> subscriptionTypeDTOS.add(new SubscriptionTypeDTO(s)));
        return subscriptionTypeDTOS;
    }
}
