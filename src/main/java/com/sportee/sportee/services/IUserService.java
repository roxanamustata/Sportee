package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.UserDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();

    void modifyUser(Integer id, Optional<String> name, Optional<String> password, Optional<String> firstName, Optional<String> lastName,
                    Optional<Date> birthDate, Optional<Integer> height);

    void insertUser(String name, String password, String firstName, String lastName, Date birthDate, int height, String role);

    void delete(Integer id);

    String getUserName(Integer id) throws NotFoundException;

}
