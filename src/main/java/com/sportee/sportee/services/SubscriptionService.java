package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.*;
import com.sportee.sportee.data.DTO.SubscriptionDTO;
import com.sportee.sportee.data.repositories.SubscriptionRepository;
import com.sportee.sportee.data.repositories.SubscriptionTypeRepository;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService implements ISubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    private SubscriptionTypeRepository subscriptionTypeRepository;
    private UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, SubscriptionTypeRepository subscriptionTypeRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptionDTOS = new ArrayList<SubscriptionDTO>();
        Iterable<Subscription> all = subscriptionRepository.findAll();
        all.forEach(s -> subscriptionDTOS.add(new SubscriptionDTO(s)));
        return subscriptionDTOS;
    }

    @Override
    public void insertSubscription(Date date, boolean valid, int subscriptionTypeId, int userId) {
        Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId);
        Optional<User> user = userRepository.findById(userId);
        Subscription subscription = Subscription.builder().date(date).valid(valid)
                .subscriptionType(subscriptionType.get()).user(user.get()).build();

        subscriptionRepository.save(subscription);
    }

    @Override
    public void deleteSubscription(Integer id) {
        subscriptionRepository.deleteById(id);
    }

    @Override
    public void editSubscription(Integer id, Optional<Date> date, Optional<Boolean> valid) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(id);

        subscription.ifPresent(s -> {
            date.ifPresent(d -> s.setDate(d));
            valid.ifPresent(v -> s.setValid(v));
            subscriptionRepository.save(s);
        });

    }

}
