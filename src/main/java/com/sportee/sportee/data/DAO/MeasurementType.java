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
public class MeasurementType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;
    private String unit;

    @OneToMany(mappedBy = "measurementType")
    private List<Measurement> measurements;

    @Builder
    public MeasurementType(int id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }
}
