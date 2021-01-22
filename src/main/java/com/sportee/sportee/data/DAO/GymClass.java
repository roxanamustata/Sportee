package com.sportee.sportee.data.DAO;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class GymClass {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private int id;
    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "gym_class_type_id")
    private GymClassType gymClassType;


    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


//    @ManyToMany(mappedBy = "gymClass")

    @OneToMany(mappedBy = "gymClass")
    List<GymClassBooking> bookings;


    @Builder(toBuilder=true)
    public GymClass(int id, LocalDateTime date, GymClassType gymClassType, Room room) {
        this.id=id;
        this.date = date;
        this.gymClassType = gymClassType;
        this.room = room;
    }


}
