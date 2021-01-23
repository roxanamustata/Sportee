package com.sportee.sportee.data.DAO;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class GymClassType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private int id;
    private String name;
    private int duration;


    @OneToMany(mappedBy = "gymClassType")
    private List<GymClass> gymClasses;

    @Builder(toBuilder = true)
    public GymClassType(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }


}

