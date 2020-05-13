package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Getter
@NoArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int height;

    private String role;


    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.birthDate = user.getBirthDate();
        this.height = user.getHeight();

        this.role = user.getRole().getName();

    }
}
