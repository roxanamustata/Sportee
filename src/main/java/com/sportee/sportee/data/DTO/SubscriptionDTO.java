package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.Subscription;
import lombok.Getter;

import java.sql.Date;

@Getter
public class SubscriptionDTO {

    private Date date;
    private boolean valid;
    private String subscriptionType;
    private String user;

    public SubscriptionDTO(Subscription subscription) {
        this.date = subscription.getDate();
        this.valid = subscription.isValid();
        this.subscriptionType = subscription.getSubscriptionType().getName();
        this.user = subscription.getUser().getFirstName() + " " +
                subscription.getUser().getLastName();
    }
}
