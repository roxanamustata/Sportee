package com.sportee.sportee.data.DAO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class Subscription {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private int id;
    private Date date;
    private boolean valid;

    @ManyToOne
    @JoinColumn(name="subscription_type_id")
    private SubscriptionType subscriptionType;

    @ManyToOne
    @JoinColumn(name="userr_id")
    private User user;

}
