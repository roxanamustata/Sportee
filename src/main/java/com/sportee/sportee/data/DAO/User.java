package com.sportee.sportee.data.DAO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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



//    @OneToOne(mappedBy = "user")
//    private SporteeMember member;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @ManyToMany
    @JoinTable(name = "participatio_to_class")
    @JoinColumn(name = "user_id")
    @JoinColumn(name = "gym_class_id")
    List<GymClass> gymClass;



    @Builder
    public User(int id, String userName, String password, String firstName, String lastName, Date birthDate, int height, Role role) {
        this.id=id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;

        this.role=role;
    }

}
