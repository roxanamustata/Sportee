package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.Subscription;
import com.sportee.sportee.data.DAO.SubscriptionType;
import com.sportee.sportee.data.DTO.SubscriptionDTO;
import com.sportee.sportee.data.DTO.SubscriptionTypeDTO;
import com.sportee.sportee.data.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService implements ISubscriptionService {
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<SubscriptionDTO>();
        Iterable<Subscription> all = subscriptionRepository.findAll();
        all.forEach(s -> subscriptionDTOS.add(new SubscriptionDTO(s)));
        return subscriptionDTOS;
    }
}
