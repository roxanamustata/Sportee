package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);

}
