package com.sportee.sportee.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class GymClassBooking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "gym_class_id")
    private GymClass gymClass;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder(toBuilder = true)
    public GymClassBooking(int id, GymClass gymClass, User user) {
        this.id = id;
        this.gymClass = gymClass;
        this.user = user;
    }


}
