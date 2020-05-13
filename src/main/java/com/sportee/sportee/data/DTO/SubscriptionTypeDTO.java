package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.SubscriptionType;
import lombok.Getter;

@Getter
public class SubscriptionTypeDTO {
    private String name;
    private int duration;
    private int price;

    public SubscriptionTypeDTO (SubscriptionType subscriptionType){
        this.name=subscriptionType.getName();
        this.duration=subscriptionType.getDuration();
        this.price=subscriptionType.getPrice();
    }

}
