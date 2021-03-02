package com.sportee.sportee.data.DAO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Embeddable
public class GymClassBookingKey implements Serializable {

    @Column(name = "user_id")
    int userId;

    @Column(name = "gym_class_id")
    int gymClassId;


    public GymClassBookingKey(int userId, int gymClassId) {
        this.userId = userId;
        this.gymClassId = gymClassId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GymClassBookingKey that = (GymClassBookingKey) o;
        return userId == that.userId && gymClassId == that.gymClassId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, gymClassId);
    }

    @Override
    public String toString() {
        return userId + "," + gymClassId;
    }
}
