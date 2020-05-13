package com.sportee.sportee.data.repositories;

import com.sportee.sportee.data.DAO.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
