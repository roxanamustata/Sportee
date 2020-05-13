package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Integer > {
        Optional<User> findByUserName(String userName);

        }
