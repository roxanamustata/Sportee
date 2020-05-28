package com.sportee.sportee.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class SubscriptionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    private int duration;
    private int price;

    @OneToMany(mappedBy = "subscriptionType")
    private List<Subscription> subscriptions;

    @Builder
    public SubscriptionType(int id, String name, int duration, int price) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
    }
}
