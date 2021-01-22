package com.sportee.sportee.data.DAO;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor

public class Room {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private int id;
    private String name;
    private int capacity;


    @OneToMany(mappedBy = "room")
    private List<GymClass> gymClasses;

    @Builder
    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
}
