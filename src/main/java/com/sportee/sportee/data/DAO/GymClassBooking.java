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

    @EmbeddedId
    GymClassBookingKey id;

    @ManyToOne
    @MapsId("gymClassId")
    @JoinColumn(name = "gym_class_id")
    private GymClass gymClass;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;


    @Builder(toBuilder = true)
    public GymClassBooking(GymClassBookingKey id, GymClass gymClass, User user) {
        this.id = id;
        this.gymClass = gymClass;
        this.user = user;
        gymClass.setAvailable(gymClass.getAvailable() - 1);
    }

    @PrePersist
    void initIdentifier() {
        if (id == null) {
            this.id = new GymClassBookingKey();
        }
    }


}
