package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.UserDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAllUsers();

//    void sendMail(Integer userId, String subject, String content);

    void modifyUser(Integer id, Optional<String> name, Optional<String> password, Optional<String> firstName, Optional<String> lastName,
                    Optional<Date> birthDate, Optional<Integer> height);

//    void insertUser(String name, String password);



    void insertUser(String name, String password, String firstName, String lastName, Date birthDate, int height, int roleId);

    void delete(Integer id);

//    void changeAddress(Integer userId, Optional<String> city, Optional<String> country, Optional<String> street);

//    metoda ne conecteaza la un serviciu exterior aplicatiei noastre
//    String getServiceTitle(Integer id) throws NotFoundException;

    String getUserName(Integer id) throws NotFoundException;

}
