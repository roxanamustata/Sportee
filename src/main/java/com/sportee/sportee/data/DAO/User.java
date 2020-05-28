package com.sportee.sportee.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int height;
    private String role;


    @OneToMany(mappedBy = "user")
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @ManyToMany
    @JoinTable(name = "participation_to_class")
    @JoinColumn(name = "user_id")
    @JoinColumn(name = "gym_class_id")
    List<GymClass> gymClass;


    @Builder
    public User(int id, String userName, String password, String firstName, String lastName,
                Date birthDate, int height, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;
        this.role = role;
    }

    public List<String> getRoleList() {
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }
}
